package by.epam.litvinko.beautysalon.service;

import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.ServiceException;

import java.util.Optional;

public interface UserService {

    Optional<User> signIn(String username, String password) throws ServiceException;
}
