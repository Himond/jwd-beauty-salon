package by.epam.litvinko.beautysalon.service.impl;

import by.epam.litvinko.beautysalon.command.RequestParameter;
import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.ClientDaoImpl;
import by.epam.litvinko.beautysalon.model.dao.impl.UserDaoImpl;
import by.epam.litvinko.beautysalon.service.UserService;
import by.epam.litvinko.beautysalon.service.converter.Converter;
import by.epam.litvinko.beautysalon.service.converter.impl.UserConverter;
import by.epam.litvinko.beautysalon.service.dto.UserDto;
import by.epam.litvinko.beautysalon.util.PasswordEncryptor;
import by.epam.litvinko.beautysalon.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private UserDaoImpl userDao = new UserDaoImpl();
    private ClientDaoImpl clientDao = new ClientDaoImpl();
    private final EntityTransaction transaction = new EntityTransaction();
    private final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
    private final Converter<UserDto, User> converter = new UserConverter();

    @Override
    public Optional<UserDto> signIn(String userName, String password) throws ServiceException {
        try {
            transaction.init(userDao);
            Optional<User> user = userDao.findUserByLogin(userName);
            transaction.end();
            if(user.isPresent() && passwordEncryptor.checkHash(password, user.get().getPassword())){
                UserDto userDto = converter.convert(user.get());
                return Optional.of(userDto);
            }
        } catch (DaoException e) {
            logger.error("Can't handle signIn request at UserService.", e);
            throw new ServiceException("Can't handle signIn request at UserService.", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> signUp(String userName, String password, String firstName, String lastName, String email, String phone) throws ServiceException {
        boolean check;
        try {
            transaction.init(userDao);
            Optional<User> user = userDao.findUserByLogin(userName);
            transaction.end();
            if (user.isEmpty()){
                transaction.initTransaction(userDao, clientDao);
                User newUser = User.newBuilder()
                        .setUserName(userName)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setEmail(email)
                        .setPassword(passwordEncryptor.getHash(password))
                        .setDateJoined(LocalDate.now()).build();
                check = userDao.create(newUser);
                Client newClient = Client.newBuilder()
                        .setUserId(newUser.getId())
                        .setPhone(phone).build();
                clientDao.create(newClient);
                transaction.commit();
                if (check){
                    newUser.setActive(true);
                    UserDto userDto = converter.convert(newUser);
                    return Optional.of(userDto);
                }
            }
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Unable to rollback.", e);
            }
            logger.error("Can't handle signIn request at UserService.", e);
            throw new ServiceException("Can't handle signIn request at UserService.", e);
        }finally {
                try {
                    transaction.endTransaction();
                } catch (DaoException e) {
                    logger.error("Error closing transaction.", e);
                }
            }
        return Optional.empty();
    }

    @Override
    public Map<String, String> isFormValid(String username, String password, String firstName, String lastName, String email, String phone) {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put(RequestParameter.USERNAME, username);
        userParameters.put(RequestParameter.PASSWORD, password);
        userParameters.put(RequestParameter.FIRSTNAME, firstName);
        userParameters.put(RequestParameter.LASTNAME, lastName);
        userParameters.put(RequestParameter.EMAIL, email);
        userParameters.put(RequestParameter.PHONE, phone);
        UserValidator.validate(userParameters);
        return userParameters;
    }

    @Override
    public boolean isPasswordsEquals(String password, String passwordRep) {
        return password.equals(passwordRep);
    }


}
