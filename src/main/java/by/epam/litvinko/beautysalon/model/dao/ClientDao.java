package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.math.BigDecimal;
import java.util.Optional;

public interface ClientDao  {

    Optional<Client> findClientByUserId(Integer id) throws DaoException;
    boolean createAccount(int clientId) throws DaoException;
    Optional<Client> topUpAccount(int clientId, String cardNumber, String amount) throws DaoException;
    Optional<Client> payForOrder(int clientId, BigDecimal price) throws DaoException;

}
