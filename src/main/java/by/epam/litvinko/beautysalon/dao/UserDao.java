package by.epam.litvinko.beautysalon.dao;

import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> signInByLogin(String login, String password) throws DaoException;
    Optional<User> signInByEmail(String email, String password) throws DaoException;
    List<User> findAllByRoll(Role role) throws DaoException;
    void updateActivity(int id, boolean isActive) throws DaoException;

}
