package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByLogin(String login) throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
    List<User> findAllByRoll(Role role) throws DaoException;

}
