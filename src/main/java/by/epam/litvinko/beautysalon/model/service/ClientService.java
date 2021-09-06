package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;

import java.util.Map;
import java.util.Optional;

public interface ClientService {

    Optional<ClientDto> signIn(UserDto user) throws ServiceException;
    Optional<ClientDto> signUp(String userName, String firstName, String lastName, String email, String phone, String password) throws ServiceException;
    Map<String, String> isFormValid(String userName, String firstName, String lastName, String email, String phone, String password);
    boolean isPasswordsEquals(String password, String passwordRep);
}
