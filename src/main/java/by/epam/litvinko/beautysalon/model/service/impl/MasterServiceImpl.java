package by.epam.litvinko.beautysalon.model.service.impl;

import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.CategoryDaoImpl;
import by.epam.litvinko.beautysalon.model.dao.impl.ClientDaoImpl;
import by.epam.litvinko.beautysalon.model.dao.impl.MasterDaoImpl;
import by.epam.litvinko.beautysalon.model.dao.impl.UserDaoImpl;
import by.epam.litvinko.beautysalon.model.service.MasterService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;
import by.epam.litvinko.beautysalon.util.PasswordEncryptor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The type Master service.
 */
public class MasterServiceImpl implements MasterService {

    private static final Logger logger = LogManager.getLogger(MasterServiceImpl.class);
    private final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();

    @Override
    public Optional<MasterDto> signIn(UserDto user) throws ServiceException {
        final MasterDaoImpl masterDao = new MasterDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(masterDao);
            Optional<Master> master = masterDao.findMasterByUserId(user.id());
            if(master.isPresent()){
                MasterDto masterDto = MasterDto.create(master.get());
                return Optional.of(masterDto);
            }
        } catch (DaoException e) {
            logger.error("Can't handle signIn request at MasterService.", e);
            throw new ServiceException("Can't handle signIn request at MasterService.", e);
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
    public List<MasterDto> allMaster() throws ServiceException {
        final MasterDaoImpl masterDao = new MasterDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        List<Master> masterList;
        List<MasterDto> masterDtoList;
        try {
            transaction.init(masterDao);
            masterList = masterDao.findAll();
            masterDtoList = masterList.stream().map(master -> new MasterDto(master.getUserId(), master.getId(), master.getRole(), master.getFirstName(),
                    master.getLastName(), master.getEmail(), master.isActive(), master.getPhoto(),
                    master.getPosition(), master.getDescription())).toList();
        } catch (DaoException e) {
            logger.error("Can't handle find all masters request at MasterService.", e);
            throw new ServiceException("Can't handle find all masters request at MasterService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return masterDtoList;
    }

    @Override
    public List<Position> allPosition() throws ServiceException {
        final MasterDaoImpl masterDao = new MasterDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        List<Position> positionList;
        try {
            transaction.init(masterDao);
            positionList = masterDao.allPosition();
        } catch (DaoException e) {
            logger.error("Can't handle find all positions request at ShopService.", e);
            throw new ServiceException("Can't handle find all positions request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return positionList;
    }

    @Override
    public boolean signUp(String userName, String firstName, String lastName, String email, String description, String position, String password) throws ServiceException {
        final MasterDaoImpl masterDao = new MasterDaoImpl();
        final UserDaoImpl userDao = new UserDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        boolean check = false;
        try {
            transaction.initTransaction(userDao, masterDao);
            Optional<User> user = userDao.findUserByLogin(userName);
            if (user.isEmpty()){
                Master newMaster = (Master) Master.newBuilder()
                        .setDescription(description)
                        .setPosition(Position.valueOf(position.toUpperCase()))
                        .setUserName(userName)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setEmail(email)
                        .setPassword(passwordEncryptor.getHash(password))
                        .setDateJoined(LocalDate.now()).build();
                check = userDao.create(newMaster);
                newMaster.setUserId(newMaster.getId());
                masterDao.create(newMaster);
                transaction.commit();
            }
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Unable to rollback.", e);
            }
            logger.error("Can't handle signUp request at MasterService.", e);
            throw new ServiceException("Can't handle signUn request at MasterService.", e);
        }finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return check;
    }
}
