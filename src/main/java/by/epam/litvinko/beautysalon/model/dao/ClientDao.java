package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * The interface Client dao.
 */
public interface ClientDao  {

    /**
     * Find client by user id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Client> findClientByUserId(Integer id) throws DaoException;

    /**
     * Create account boolean.
     *
     * @param clientId the client id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createAccount(int clientId) throws DaoException;

    /**
     * Top up account optional.
     *
     * @param clientId   the client id
     * @param cardNumber the card number
     * @param amount     the amount
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Client> topUpAccount(int clientId, String cardNumber, String amount) throws DaoException;

    /**
     * Pay for order optional.
     *
     * @param clientId the client id
     * @param price    the price
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Client> payForOrder(int clientId, BigDecimal price) throws DaoException;

}
