package by.epam.litvinko.beautysalon.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.service.dto.UserDto;

import java.util.Optional;

public interface MasterService {

    Optional<MasterDto> signIn(UserDto user) throws ServiceException;

}
