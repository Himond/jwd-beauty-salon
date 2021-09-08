package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> signIn(String userName, String password) throws ServiceException;
    boolean isEmailValid(String email);
    boolean forgetPassword(String email) throws ServiceException;
    void updateUserPhoto(String userId, String photo) throws ServiceException;
    boolean editPassword(String id, String oldPassword, String newPassword) throws ServiceException;
    boolean isPasswordsEquals(String password, String passwordRep);
}
