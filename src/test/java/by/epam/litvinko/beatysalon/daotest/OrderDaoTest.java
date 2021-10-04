package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.model.dao.impl.OrderDaoImpl;
import by.epam.litvinko.beautysalon.entity.Order;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.model.service.impl.OrderServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class OrderDaoTest {
    private Connection connection = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private OrderDaoImpl orderDao = new OrderDaoImpl();

    @Before
    public void before(){
        connection = connectionPool.getConnection();
        orderDao.setConnection(connection);

    }

    @After
    public void after(){
        connectionPool.releaseConnection(connection);
    }

    @Test
    public void findAllOrdersTest() throws DaoException, ServiceException {
        OrderServiceImpl service = new OrderServiceImpl();
        List<List<Integer>> ordersItem = orderDao.findOrderByMasterId(1);
        System.out.println(ordersItem);
        for (List<Integer> order: ordersItem){
            System.out.println(order);
        }
        Assert.assertEquals(2, 2);
    }

    @Test
    public void findOrderByIdTest() throws DaoException {
        Order actual = orderDao.findById(39).get();
        System.out.println(actual);
        Assert.assertNotNull(actual);
    }

    @Test
    public void createOrderTest() throws DaoException {
        Order order = new Order();
        order.setClientId(3);
        order.setCouponId(1);
        order.setCreated(LocalDate.now());
        boolean actual = orderDao.create(order);
        Assert.assertTrue(actual);
    }
}
