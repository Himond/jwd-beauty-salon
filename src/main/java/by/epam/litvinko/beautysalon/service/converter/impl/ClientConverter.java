package by.epam.litvinko.beautysalon.service.converter.impl;

import by.epam.litvinko.beautysalon.entity.Client;
import by.epam.litvinko.beautysalon.service.converter.Converter;
import by.epam.litvinko.beautysalon.service.dto.ClientDto;


public class ClientConverter implements Converter<ClientDto, Client> {

    @Override
    public ClientDto convert(Client entity) {
        ClientDto client = new ClientDto();
        client.setRole(entity.getRole());
        client.setFirstName(entity.getFirstName());
        client.setLastName(entity.getLastName());
        client.setEmail(entity.getEmail());
        client.setActive(entity.isActive());
        client.setPhoto(entity.getPhoto());
        client.setPhone(entity.getPhone());
        client.setDateOfBirth(entity.getDateOfBirthday());
        return client;
    }



}
