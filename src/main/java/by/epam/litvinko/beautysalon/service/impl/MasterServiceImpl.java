package by.epam.litvinko.beautysalon.service.impl;

import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.MasterDaoImpl;
import by.epam.litvinko.beautysalon.service.MasterService;
import by.epam.litvinko.beautysalon.service.converter.Converter;
import by.epam.litvinko.beautysalon.service.converter.impl.MasterConverter;
import by.epam.litvinko.beautysalon.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.service.dto.UserDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class MasterServiceImpl implements MasterService {

    private static final Logger logger = LogManager.getLogger(MasterServiceImpl.class);
    private final MasterDaoImpl masterDao = new MasterDaoImpl();
    private final EntityTransaction transaction = new EntityTransaction();
    private final Converter<MasterDto, Master> converter = new MasterConverter();


    @Override
    public Optional<MasterDto> signIn(UserDto user) throws ServiceException {

        try {
            transaction.init(masterDao);
            Optional<Master> master = masterDao.findMasterByUserId(user.getId());
            transaction.end();
            if(master.isPresent()){
                MasterDto masterDto = converter.convert(master.get());
                return Optional.of(masterDto);
            }
        } catch (DaoException e) {
            logger.error("Can't handle signIn request at MasterService.", e);
            throw new ServiceException("Can't handle signIn request at MasterService.", e);
        }
        return Optional.empty();
    }
}
