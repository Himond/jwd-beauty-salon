package by.epam.litvinko.beautysalon.dao;

import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.Optional;

public interface ClientDao {

    void updateRegular(int userId, boolean isRegular) throws DaoException;
    Optional<Client> findClientByUserId(Integer userId) throws DaoException;

}
