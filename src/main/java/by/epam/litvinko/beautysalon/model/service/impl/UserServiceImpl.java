package by.epam.litvinko.beautysalon.model.service.impl;

import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.UserDaoImpl;
import by.epam.litvinko.beautysalon.model.service.UserService;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;
import by.epam.litvinko.beautysalon.util.MailSender;
import by.epam.litvinko.beautysalon.util.PasswordEncryptor;
import by.epam.litvinko.beautysalon.model.validator.UserValidator;
import by.epam.litvinko.beautysalon.model.validator.impl.UserValidatorImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
    private final UserValidator validator = UserValidatorImpl.getInstance();

    @Override
    public Optional<UserDto> signIn(String userName, String password) throws ServiceException {
        final UserDaoImpl userDao = new UserDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(userDao);
            Optional<User> user = userDao.findUserByLogin(userName);
            transaction.end();
            if(user.isPresent() && passwordEncryptor.checkHash(password, user.get().getPassword())){
                UserDto userDto = UserDto.create(user.get());
                return Optional.of(userDto);
            }
        } catch (DaoException e) {
            logger.error("Can't handle signIn request at UserService.", e);
            throw new ServiceException("Can't handle signIn request at UserService.", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean forgetPassword(String email) throws ServiceException {
        final UserDaoImpl userDao = new UserDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(userDao);
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            transaction.end();
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String newPassword = PasswordEncryptor.generateRandomPassword();
                userDao.setPasswordById(user.getId(), passwordEncryptor.getHash(newPassword));
                MailSender.send(email, MailSender.messageForgetPassword(user.getUserName(), newPassword));
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't handle forgetPassword request at UserService", e);
        }
        return false;
    }

    @Override
    public boolean editPassword(String id, String oldPassword, String newPassword) throws ServiceException {
        final UserDaoImpl userDao = new UserDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        int userId = Integer.parseInt(id);
        try {
            transaction.init(userDao);
            Optional<User> optionalUser = userDao.findById(userId);
            transaction.end();
            if (optionalUser.isPresent() && passwordEncryptor.checkHash(oldPassword, optionalUser.get().getPassword())) {
                userDao.setPasswordById(userId, passwordEncryptor.getHash(newPassword));
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't handle editPassword request at UserService", e);
        }
        return false;
    }

    @Override
    public boolean isEmailValid(String email) {
        return validator.validateEmail(email);
    }

    @Override
    public boolean isPasswordsEquals(String password, String passwordRep) {
        return password.equals(passwordRep);
    }



}
