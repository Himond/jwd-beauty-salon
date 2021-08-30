package by.epam.litvinko.beatysalon.daotest;

import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.*;
import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.junit.Test;

import java.time.LocalDate;

public class OrderItemDaoTest {

    private OrderDaoImpl orderDao = new OrderDaoImpl();
    private CartDaoImpl itemDao = new CartDaoImpl();
    private MasterDaoImpl masterDao = new MasterDaoImpl();
    private ProvideServiceDaoImpl service = new ProvideServiceDaoImpl();

    @Test
    public void createOrderTest() throws DaoException {

        EntityTransaction transaction = new EntityTransaction();
        EntityTransaction transactionMaster = new EntityTransaction();
        EntityTransaction transactionService = new EntityTransaction();
        transaction.initTransaction(orderDao, itemDao);
        transactionMaster.init(masterDao);
        transactionService.init(service);

        Master master1 = masterDao.findById(1).get();
        Master master2 = masterDao.findById(2).get();
        transactionMaster.end();

        ProvideService product1 = service.findById(1).get();
        ProvideService product2 = service.findById(2).get();
        ProvideService product3 = service.findById(3).get();
        transactionService.end();


        Cart cart = new Cart();
        cart.addProvideService(product1, master1);
        cart.addProvideService(product2, master2);
        cart.addProvideService(product3, master1);

        Order order = new Order();
        order.setCouponId(1);
        order.setClientId(3);
        order.setCreated(LocalDate.now());

        try {
            orderDao.create(order);
            if (order.getId() != 0){
                cart.setOrderID(order.getId());
                itemDao.create(cart);
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

    }
}
