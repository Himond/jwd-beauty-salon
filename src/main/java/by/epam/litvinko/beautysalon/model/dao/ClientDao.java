package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.Optional;

public interface ClientDao {

    Optional<Client> findClientByUserId(Integer id) throws DaoException;

}
