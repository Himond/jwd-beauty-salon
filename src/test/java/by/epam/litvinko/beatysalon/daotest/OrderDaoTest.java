package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.dao.impl.OrderDaoImpl;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class OrderDaoTest {
    private Connection connection = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private OrderDaoImpl orderDao = new OrderDaoImpl();

    @Before
    public void before(){
        connectionPool.initPool();
        connection = connectionPool.getConnection();
        orderDao.setConnection(connection);

    }

    @After
    public void after(){
        connectionPool.releaseConnection(connection);
    }

    @Test
    public void findAllOrdersTest() throws DaoException {
        int actual = orderDao.findAll().size();
        Assert.assertEquals(2, actual);
    }
}
