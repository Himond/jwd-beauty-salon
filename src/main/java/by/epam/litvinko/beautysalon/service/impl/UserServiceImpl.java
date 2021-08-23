package by.epam.litvinko.beautysalon.service.impl;

import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.UserDaoImpl;
import by.epam.litvinko.beautysalon.service.UserService;
import by.epam.litvinko.beautysalon.util.PasswordEncryptor;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao = new UserDaoImpl();
    private EntityTransaction transaction = new EntityTransaction();
    private final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();

    @Override
    public Optional<User> signIn(String login, String password) throws ServiceException {
        System.out.println("_______________service");

        User user;

        try {
            transaction.init(userDao);
            user = userDao.findUserByLogin(login).get();
            transaction.end();
            System.out.println(user);
            if (!passwordEncryptor.checkHash(password, user.getPassword())) {
                return Optional.empty();
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.of(user);
    }

}
