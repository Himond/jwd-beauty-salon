package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.model.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.model.dao.impl.UserDaoImpl;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.time.LocalDate;

public class UserDaoTest {

    private Connection connection = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private UserDaoImpl userDao = new UserDaoImpl();
    private User expected;
    private User newUser;

    @Before
    public void before(){
        connection = connectionPool.getConnection();

        userDao.setConnection(connection);


        User.Builder builder = User.newBuilder();
        builder.setId(3)
                .setRole(Role.CLIENT)
                .setUserName("client1")
                .setPassword("$2a$12$4tIZJUQjXKo6xR6/yY6bZeVPVS1d9shjIaevwPnhsQ48adG3ophx2")
                .setEmail("client1@mail.ru")
                .setFirstName("client1")
                .setLastName("client1")
                .setIsActive(true)
                .setDateJoined(LocalDate.parse("2021-08-09"))
                .setPhoto(null);
        expected = builder.build();

        User.Builder builder1 = User.newBuilder();
        builder1.setId(17)
                .setRole(Role.MASTER)
                .setUserName("master2")
                .setPassword("master2")
                .setEmail("master23@mail.ru")
                .setFirstName("master2")
                .setLastName("master2")
                .setIsActive(true);
        newUser = builder1.build();

    }

    @After
    public void after(){
        connectionPool.releaseConnection(connection);
    }

    @Test
    public void findUserByIdTest() throws DaoException {
        User actual = userDao.findById(3).get();
        Assert.assertEquals(expected, actual);;
    }

    @Test
    public void findAllUsersTest() throws DaoException {
        int actual = userDao.findAll().size();
        Assert.assertEquals(4, actual);
    }

    @Test
    public void createUserTest() throws DaoException {
        boolean actual = userDao.create(newUser);
        Assert.assertTrue(actual);
    }


    @Test
    public void deleteUserByIdTest() throws DaoException {
        boolean actual = userDao.delete(5);
        Assert.assertTrue(actual);
    }

    @Test
    public void updateUserByIdTest() throws DaoException {

        newUser.setUserName("update1");
        newUser.setFirstName("update1");
        newUser.setLastName("update1");

        User actual = userDao.update(newUser).get();
        Assert.assertEquals(actual.getUserName(), newUser.getUserName());

    }

    @Test
    public void findAllUsersByRoleTest() throws DaoException {
        int actual = userDao.findAllByRoll(Role.CLIENT).size();
        Assert.assertEquals(2, actual);
    }

    @Test
    public void signInByLoginTest() throws DaoException {
        User actual = userDao.findUserByLogin("client1").get();
        Assert.assertEquals(expected, actual);;
    }

    @Test
    public void signInByEmailTest() throws DaoException {
        User actual = userDao.findUserByEmail("client1@mail.ru").get();
        Assert.assertEquals(expected, actual);;
    }
}
