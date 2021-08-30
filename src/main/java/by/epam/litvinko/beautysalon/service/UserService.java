package by.epam.litvinko.beautysalon.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.service.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> signIn(String username, String password) throws ServiceException;
    Optional<UserDto> signUp(String username, String firstName, String lastName, String email, String phone, String password) throws ServiceException;

}
