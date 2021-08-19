package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.dao.impl.*;
import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.time.LocalDate;

public class OrderItemDaoTest {

    private Connection connectionOrder = null;
    private Connection connectionItem = null;
    private Connection connectionService = null;
    private Connection connectionMaster = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private OrderDaoImpl orderDao = new OrderDaoImpl();
    private CartDaoImpl itemDao = new CartDaoImpl();
    private MasterDaoImpl masterDao = new MasterDaoImpl();
    private ProvideServiceDaoImpl service = new ProvideServiceDaoImpl();
    private Category expected;
    private Category newClient;


    @Before
    public void before(){
        connectionPool.initPool();
        connectionOrder = connectionPool.getConnection();
        connectionItem = connectionPool.getConnection();
        connectionService = connectionPool.getConnection();
        connectionMaster = connectionPool.getConnection();

        orderDao.setConnection(connectionOrder);
        itemDao.setConnection(connectionItem);
        masterDao.setConnection(connectionMaster);
        service.setConnection(connectionService);

    }

    @After
    public void after(){
        connectionPool.releaseConnection(connectionOrder);
        connectionPool.releaseConnection(connectionItem);
        connectionPool.releaseConnection(connectionMaster);
        connectionPool.releaseConnection(connectionService);
    }

    @Test
    public void createOrderTest() throws DaoException {
        Master master1 = masterDao.findById(1).get();
        System.out.println(master1);
        Master master2 = masterDao.findById(2).get();
        System.out.println(master2);

        ProvideService product1 = service.findById(1).get();
        System.out.println(product1);
        ProvideService product2 = service.findById(2).get();
        System.out.println(product2);
        ProvideService product3 = service.findById(3).get();


        Cart cart = new Cart();
        cart.addProvideService(product1, master1);
        cart.addProvideService(product2, master2);
        cart.addProvideService(product3, master1);

        Order order = new Order();
        order.setCouponId(1);
        order.setClientId(3);
        order.setCreated(LocalDate.now());

        orderDao.create(order);
        System.out.println(order.getId());
        if (order.getId() != 0){
            cart.setOrderID(order.getId());
            itemDao.create(cart);
        }

        //int actual = categoryDao.findAll().size();
        //Assert.assertEquals(5, actual);
    }
}
