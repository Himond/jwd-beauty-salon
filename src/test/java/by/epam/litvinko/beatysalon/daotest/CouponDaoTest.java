package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.model.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.model.dao.impl.CouponDaoImpl;
import by.epam.litvinko.beautysalon.entity.Coupon;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CouponDaoTest {

    private Connection connection = null;
    private DatabaseConnectionPool connectionPool = DatabaseConnectionPool.getInstance();
    private CouponDaoImpl couponDao = new CouponDaoImpl();

    @Before
    public void before(){
        connection = connectionPool.getConnection();
        couponDao.setConnection(connection);

    }

    @After
    public void after(){
        connectionPool.releaseConnection(connection);
    }

    @Test
    public void findAllCouponsTest() throws DaoException {
        int actual = couponDao.findAll().size();
        Assert.assertEquals(4, actual);
    }

    @Test
    public void findAllCouponByIdTest() throws DaoException {
        Coupon actual = couponDao.findById(2).get();
        Assert.assertNotNull(actual);
    }

    @Test
    public void createCouponTest() throws DaoException {
        Coupon coupon = new Coupon();
        coupon.setCode("WWW3");
        coupon.setValidFrom(LocalDate.now());
        coupon.setValidTo(LocalDate.now());
        coupon.setDiscount(25);
        boolean actual = couponDao.create(coupon);
        Assert.assertTrue(actual);
    }

    @Test
    public void updateCouponTest() throws DaoException {
        Coupon coupon = new Coupon();
        coupon.setId(3);
        coupon.setCode("WWW2");
        coupon.setValidFrom(LocalDate.now());
        coupon.setValidTo(LocalDate.now());
        coupon.setDiscount(25);
        coupon.setActive(true);
        Coupon actual = couponDao.update(coupon).get();
        Assert.assertNotNull(actual);
    }

}
