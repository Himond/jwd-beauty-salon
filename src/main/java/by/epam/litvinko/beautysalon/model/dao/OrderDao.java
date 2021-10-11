package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Cart;
import by.epam.litvinko.beautysalon.entity.Order;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao {

    /**
     * Create order item boolean.
     *
     * @param cart the cart
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createOrderItem(Cart cart) throws DaoException;

    /**
     * Find order by client id list.
     *
     * @param id the id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findOrderByClientId(int id) throws DaoException;

    /**
     * Find order by master id list.
     *
     * @param id the id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<List<Integer>> findOrderByMasterId(int id) throws DaoException;

    /**
     * Find all order for admin list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<List<Integer>> findAllOrderForAdmin() throws DaoException;

    /**
     * Add master in order boolean.
     *
     * @param orderItemId the order item id
     * @param masterId    the master id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean addMasterInOrder(int orderItemId, int masterId) throws DaoException;

    /**
     * Completed order boolean.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean completedOrder(int orderId) throws DaoException;
}
