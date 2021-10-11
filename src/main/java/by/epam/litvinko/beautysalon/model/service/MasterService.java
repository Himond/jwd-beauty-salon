package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.entity.Position;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * The interface Master service.
 */
public interface MasterService {

    /**
     * Sign in optional.
     *
     * @param user the user
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<MasterDto> signIn(UserDto user) throws ServiceException;

    /**
     * All master list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<MasterDto> allMaster() throws ServiceException;

    /**
     * All position list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Position> allPosition() throws ServiceException;

    /**
     * Sign up boolean.
     *
     * @param userName    the user name
     * @param firstName   the first name
     * @param lastName    the last name
     * @param email       the email
     * @param description the description
     * @param position    the position
     * @param password    the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean signUp(String userName, String firstName, String lastName, String email, String description, String position, String password) throws ServiceException;

}
