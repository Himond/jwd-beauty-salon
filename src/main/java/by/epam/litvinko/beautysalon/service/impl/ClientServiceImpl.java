package by.epam.litvinko.beautysalon.service.impl;

import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.ClientDaoImpl;
import by.epam.litvinko.beautysalon.service.ClientService;
import by.epam.litvinko.beautysalon.service.converter.Converter;
import by.epam.litvinko.beautysalon.service.converter.impl.ClientConverter;
import by.epam.litvinko.beautysalon.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.service.dto.UserDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);
    private final ClientDaoImpl clientDao = new ClientDaoImpl();
    private final EntityTransaction transaction = new EntityTransaction();
    private final Converter<ClientDto, Client> converter = new ClientConverter();

    @Override
    public Optional<ClientDto> signIn(UserDto user) throws ServiceException {

        try {
            transaction.init(clientDao);
            Optional<Client> client = clientDao.findClientByUserId(user.getId());
            transaction.end();
            if(client.isPresent()){
                ClientDto clientDto = converter.convert(client.get());
                return Optional.of(clientDto);
            }
        } catch (DaoException e) {
            logger.error("Can't handle signIn request at ClientService.", e);
            throw new ServiceException("Can't handle signIn request at ClientService.", e);
        }
        return Optional.empty();



    }

}
