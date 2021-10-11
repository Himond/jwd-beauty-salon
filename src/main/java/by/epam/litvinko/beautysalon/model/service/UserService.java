package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Sign in optional.
     *
     * @param userName the user name
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<UserDto> signIn(String userName, String password) throws ServiceException;

    /**
     * Is email valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isEmailValid(String email);

    /**
     * Forget password boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean forgetPassword(String email) throws ServiceException;

    /**
     * Update user photo.
     *
     * @param userId the user id
     * @param photo  the photo
     * @throws ServiceException the service exception
     */
    void updateUserPhoto(String userId, String photo) throws ServiceException;

    /**
     * Edit password boolean.
     *
     * @param id          the id
     * @param oldPassword the old password
     * @param newPassword the new password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean editPassword(String id, String oldPassword, String newPassword) throws ServiceException;

    /**
     * Is passwords equals boolean.
     *
     * @param password    the password
     * @param passwordRep the password rep
     * @return the boolean
     */
    boolean isPasswordsEquals(String password, String passwordRep);
}
