package by.epam.litvinko.beautysalon.model.service.dto;

import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.entity.Role;

import java.io.Serializable;
import java.time.LocalDate;

public record ClientDto(int userId, Role role, String firstName, String lastName, String email,
                        boolean isActive, String photo, String phone, LocalDate dateOfBirth) implements Serializable, Cloneable {

    public static ClientDto create(Client client){
        ClientDto clientDto = new ClientDto(client.getUserId(), client.getRole(), client.getFirstName(),
                client.getLastName(), client.getEmail(), client.isActive(), client.getPhoto(),
                client.getPhone(), client.getDateOfBirthday());
        return clientDto;

    }

}
