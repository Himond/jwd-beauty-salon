package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Cart;
import by.epam.litvinko.beautysalon.entity.Order;
import by.epam.litvinko.beautysalon.entity.ProvideService;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    boolean createOrderItem(Cart cart) throws DaoException;
    List<Order> findOrderByClientId(int id) throws DaoException;

}
