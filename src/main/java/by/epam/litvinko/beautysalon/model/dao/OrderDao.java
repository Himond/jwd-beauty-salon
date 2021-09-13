package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Cart;
import by.epam.litvinko.beautysalon.exception.DaoException;

public interface OrderDao {

    boolean createOrderItem(Cart cart) throws DaoException;

}
