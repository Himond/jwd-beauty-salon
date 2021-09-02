package by.epam.litvinko.beautysalon.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.service.dto.UserDto;

import java.util.Optional;

public interface ClientService {

    Optional<ClientDto> signIn(UserDto user) throws ServiceException;

}
