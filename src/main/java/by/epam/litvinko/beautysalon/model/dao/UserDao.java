package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao {

    /**
     * Find user by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Update user photo by id.
     *
     * @param userId the user id
     * @param photo  the photo
     * @throws DaoException the dao exception
     */
    void updateUserPhotoById(String userId, String photo) throws DaoException;

    /**
     * Find all by roll list.
     *
     * @param role the role
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllByRoll(Role role) throws DaoException;

    /**
     * Sets password by id.
     *
     * @param id       the id
     * @param password the password
     * @throws DaoException the dao exception
     */
    void setPasswordById(Integer id, String password) throws DaoException;


}
