package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.dao.impl.ProvideServiceDaoImpl;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.entity.ProvideService;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class ServiceDaoTest {

    private Connection connection = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private ProvideServiceDaoImpl serviceDao = new ProvideServiceDaoImpl();
    private ProvideService expected;
    private ProvideService newClient;


    @Before
    public void before(){
        connectionPool.initPool();
        connection = connectionPool.getConnection();
        serviceDao.setConnection(connection);

    }

    @After
    public void after(){
        connectionPool.releaseConnection(connection);
    }

    @Test
    public void findAllServicesTest() throws DaoException {
        int actual = serviceDao.findAll().size();
        Assert.assertEquals(10, actual);
    }

    @Test
    public void findAllCategoryByIdTest() throws DaoException {
        ProvideService actual = serviceDao.findById(2).get();
        Assert.assertNotNull(actual);
    }


    @Test
    public void createCategoryTest() throws DaoException {
        Category category = new Category();
        category.setName("test");
        //boolean actual = serviceDao.create(category);
        //Assert.assertTrue(actual);
    }
}
