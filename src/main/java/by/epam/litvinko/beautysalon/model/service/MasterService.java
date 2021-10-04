package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.entity.Position;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface MasterService {

    Optional<MasterDto> signIn(UserDto user) throws ServiceException;
    List<MasterDto> allMaster() throws ServiceException;
    List<Position> allPosition() throws ServiceException;
    boolean signUp(String userName, String firstName, String lastName, String email, String description, String position, String password) throws ServiceException;

}
