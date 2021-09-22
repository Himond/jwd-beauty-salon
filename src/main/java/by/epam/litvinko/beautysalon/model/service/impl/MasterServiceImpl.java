package by.epam.litvinko.beautysalon.model.service.impl;

import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.MasterDaoImpl;
import by.epam.litvinko.beautysalon.model.service.MasterService;
import by.epam.litvinko.beautysalon.model.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class MasterServiceImpl implements MasterService {

    private static final Logger logger = LogManager.getLogger(MasterServiceImpl.class);

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
}
