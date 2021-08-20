package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.model.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.model.dao.impl.ClientDaoImpl;
import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.*;

import java.sql.Connection;
import java.time.LocalDate;

public class ClientDaoTest {

    private Connection connection = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private ClientDaoImpl clientDao = new ClientDaoImpl();
    private Client expected;
    private Client newClient;


    @Before
    public void before(){
        connection = connectionPool.getConnection();
        clientDao.setConnection(connection);

        Client.Builder builder1 = Client.newBuilder();
        builder1.setUserId(5)
                .setPhone("297858858")
                .setDateOfBirthday(LocalDate.now())
                .setIsRegular(true);
        newClient = builder1.build();
    }

    @After
    public void after(){
        connectionPool.releaseConnection(connection);
    }


    @Test
    public void findAllClientsTest() throws DaoException {
        int actual = clientDao.findAll().size();
        Assert.assertEquals(3, actual);
    }

    @Test
    public void findClientByIdTest() throws DaoException {
        User actual = clientDao.findById(1).get();
        Assert.assertNotNull(actual);
    }

    @Test
    public void createClientTest() throws DaoException {
        boolean actual = clientDao.create(newClient);
        Assert.assertTrue(actual);
    }

    @Test
    public void updateClientRegularTest() throws DaoException {
        boolean actual = clientDao.create(newClient);
        Assert.assertTrue(actual);
    }

}
