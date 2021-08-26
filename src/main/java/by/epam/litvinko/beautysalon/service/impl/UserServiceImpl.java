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
        Optional<User> user;
        try {
            transaction.init(userDao);
            user = userDao.findUserByLogin(login);
            transaction.end();
            if(user.isPresent() && passwordEncryptor.checkHash(password, user.get().getPassword())){
                return user;
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't handle signIn request at UserService", e);
        }
        return Optional.empty();
    }

}
