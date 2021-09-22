package by.epam.litvinko.beautysalon.model.service.impl;

import by.epam.litvinko.beautysalon.controller.command.RequestParameter;
import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.ClientDaoImpl;
import by.epam.litvinko.beautysalon.model.dao.impl.UserDaoImpl;
import by.epam.litvinko.beautysalon.model.service.ClientService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;
import by.epam.litvinko.beautysalon.util.PasswordEncryptor;
import by.epam.litvinko.beautysalon.model.validator.SalonValidator;
import by.epam.litvinko.beautysalon.model.validator.impl.SalonValidatorImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    private final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
    private final SalonValidator validator = SalonValidatorImpl.getInstance();

    @Override
    public Optional<ClientDto> signIn(UserDto user) throws ServiceException {
        final ClientDaoImpl clientDao = new ClientDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(clientDao);
            Optional<Client> client = clientDao.findClientByUserId(user.id());
            if(client.isPresent()){
                ClientDto clientDto = ClientDto.create(client.get());
                return Optional.of(clientDto);
            }
        } catch (DaoException e) {
            logger.error("Can't handle signIn request at ClientService.", e);
            throw new ServiceException("Can't handle signIn request at ClientService.", e);
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
    public Optional<ClientDto> signUp(String userName, String firstName, String lastName, String email, String phone, String password) throws ServiceException {
        final ClientDaoImpl clientDao = new ClientDaoImpl();
        final UserDaoImpl userDao = new UserDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        boolean check;
        try {
            transaction.initTransaction(userDao, clientDao);
            Optional<User> user = userDao.findUserByLogin(userName);
            if (user.isEmpty()){
                Client newClient = (Client) Client.newBuilder()
                        .setPhone(phone)
                        .setUserName(userName)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setEmail(email)
                        .setPassword(passwordEncryptor.getHash(password))
                        .setDateJoined(LocalDate.now()).build();
                check = userDao.create(newClient);
                newClient.setUserId(newClient.getId());
                clientDao.create(newClient);
                transaction.commit();
                if (check){
                    newClient.setActive(true);
                    ClientDto clientDto = ClientDto.create(newClient);
                    return Optional.of(clientDto);
                }
            }
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Unable to rollback.", e);
            }
            logger.error("Can't handle signUp request at ClientService.", e);
            throw new ServiceException("Can't handle signUn request at ClientService.", e);
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
    public Optional<ClientDto> editData(String userId, String firstName, String lastName, String email, String phone, String birthday) throws ServiceException {
        final ClientDaoImpl clientDao = new ClientDaoImpl();
        final UserDaoImpl userDao = new UserDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initTransaction(userDao, clientDao);
            Client newClient = (Client) Client.newBuilder()
                    .setUserId(Integer.parseInt(userId))
                    .setPhone(phone)
                    .setDateOfBirthday(LocalDate.parse(birthday))
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setId(Integer.parseInt(userId))
                    .build();
            userDao.update(newClient);
            Optional<Client> editClient = clientDao.update(newClient);
            transaction.commit();
            if (editClient.isPresent()){
                ClientDto clientDto = ClientDto.create(editClient.get());
                return Optional.of(clientDto);
            }
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Unable to rollback.", e);
            }
            logger.error("Can't handle signIn request at ClientService.", e);
            throw new ServiceException("Can't handle signIn request at ClientService.", e);
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
    public Optional<ClientDto> findClientByUserId(String userId) throws ServiceException {
        final ClientDaoImpl clientDao = new ClientDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(clientDao);
            Optional<Client> client = clientDao.findClientByUserId(Integer.parseInt(userId));
            if(client.isPresent()){
                ClientDto clientDto = ClientDto.create(client.get());
                return Optional.of(clientDto);
            }
        } catch (DaoException e) {
            logger.error("Can't handle find client request at ClientService.", e);
            throw new ServiceException("Can't handle find client request at ClientService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return Optional.empty();
    }

    @Override
    public Map<String, String> isFormValid(String username, String firstName, String lastName, String email, String phone, String password) {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put(RequestParameter.USERNAME, username);
        userParameters.put(RequestParameter.PASSWORD, password);
        userParameters.put(RequestParameter.FIRSTNAME, firstName);
        userParameters.put(RequestParameter.LASTNAME, lastName);
        userParameters.put(RequestParameter.EMAIL, email);
        userParameters.put(RequestParameter.PHONE, phone);
        validator.validate(userParameters);
        return userParameters;
    }

    @Override
    public Map<String, String> isEditFormValid(String firstName, String lastName, String email, String phone, String date) {
        Map<String, String> editParameters = new HashMap<>();
        editParameters.put(RequestParameter.FIRSTNAME, firstName);
        editParameters.put(RequestParameter.LASTNAME, lastName);
        editParameters.put(RequestParameter.EMAIL, email);
        editParameters.put(RequestParameter.PHONE, phone);
        editParameters.put(RequestParameter.BIRTHDAY, date);
        validator.validateEditData(editParameters);
        return editParameters;
    }

    @Override
    public boolean isPasswordsEquals(String password, String passwordRep) {
        return password.equals(passwordRep);
    }


}
