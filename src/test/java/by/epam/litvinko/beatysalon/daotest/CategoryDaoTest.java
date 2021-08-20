package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.model.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.model.dao.impl.CategoryDaoImpl;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.*;

import java.sql.Connection;


public class CategoryDaoTest {

    private Connection connection = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private CategoryDaoImpl categoryDao = new CategoryDaoImpl();
    private Category expected;
    private Category newClient;


    @Before
    public void before(){
        connection = connectionPool.getConnection();
        categoryDao.setConnection(connection);

    }

    @After
    public void after(){
        connectionPool.releaseConnection(connection);
    }

    @Test
    public void findAllCategoriesTest() throws DaoException {
        int actual = categoryDao.findAll().size();
        Assert.assertEquals(5, actual);
    }

    @Test
    public void findAllCategoryByIdTest() throws DaoException {
        Category actual = categoryDao.findById(2).get();
        Assert.assertNotNull(actual);
    }

    @Test
    public void createCategoryTest() throws DaoException {
        Category category = new Category();
        category.setName("test");
        boolean actual = categoryDao.create(category);
        Assert.assertTrue(actual);
    }
}
