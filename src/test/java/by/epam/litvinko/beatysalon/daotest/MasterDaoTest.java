package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.dao.impl.MasterDaoImpl;
import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.entity.Position;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class MasterDaoTest {


    private Connection connection = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private MasterDaoImpl masterDao = new MasterDaoImpl();
    private Master expected;
    private Master newMaster;


    @Before
    public void before(){
        connectionPool.initPool();
        connection = connectionPool.getConnection();
        masterDao.setConnection(connection);

        Master.Builder builder1 = Master.newBuilder();
        builder1.setUserId(6)
                .setPosition(Position.VISAGISTE)
                .setDescription("Visagiste of the highest category");
        newMaster = builder1.build();
    }

    @After
    public void after(){
        connectionPool.releaseConnection(connection);
    }

    @Test
    public void findAllMasterTest() throws DaoException {
        int actual = masterDao.findAll().size();
        Assert.assertEquals(1, actual);
    }

    @Test
    public void findMasterByIdTest() throws DaoException {
        User actual = masterDao.findById(1).get();
        Assert.assertNotNull(actual);
    }

    @Test
    public void findClientByUserIdTest() throws DaoException {
        User actual = masterDao.findMasterByUserId(2).get();
        Assert.assertNotNull(actual);
    }

    @Test
    public void createMasterTest() throws DaoException {
        boolean actual = masterDao.create(newMaster);
        Assert.assertTrue(actual);
    }


}
