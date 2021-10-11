package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;

import java.util.Map;
import java.util.Optional;

/**
 * The interface Client service.
 */
public interface ClientService {

    /**
     * Sign in optional.
     *
     * @param user the user
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<ClientDto> signIn(UserDto user) throws ServiceException;

    /**
     * Sign up optional.
     *
     * @param userName  the user name
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param phone     the phone
     * @param password  the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<ClientDto> signUp(String userName, String firstName, String lastName, String email, String phone, String password) throws ServiceException;

    /**
     * Edit data optional.
     *
     * @param userId    the user id
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param phone     the phone
     * @param birthday  the birthday
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<ClientDto> editData(String userId, String firstName, String lastName, String email, String phone, String birthday) throws ServiceException;

    /**
     * Find client by user id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<ClientDto> findClientByUserId(String userId) throws ServiceException;

    /**
     * Is form valid map.
     *
     * @param userName  the user name
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param phone     the phone
     * @param password  the password
     * @return the map
     */
    Map<String, String> isFormValid(String userName, String firstName, String lastName, String email, String phone, String password);

    /**
     * Is edit form valid map.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param phone     the phone
     * @param date      the date
     * @return the map
     */
    Map<String, String> isEditFormValid(String firstName, String lastName, String email, String phone, String date);

    /**
     * Is passwords equals boolean.
     *
     * @param password    the password
     * @param passwordRep the password rep
     * @return the boolean
     */
    boolean isPasswordsEquals(String password, String passwordRep);

    /**
     * Top up account optional.
     *
     * @param clientId   the client id
     * @param cardNumber the card number
     * @param amount     the amount
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<ClientDto> topUpAccount(int clientId, String cardNumber, String amount) throws ServiceException;

}
