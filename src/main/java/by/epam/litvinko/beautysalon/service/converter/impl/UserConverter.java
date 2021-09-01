package by.epam.litvinko.beautysalon.service.converter.impl;

import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.service.converter.Converter;
import by.epam.litvinko.beautysalon.service.dto.UserDto;

public class UserConverter implements Converter<UserDto, User> {

    @Override
    public UserDto convert(User entity) {
        UserDto userDto = new UserDto();
        userDto.setRole(entity.getRole());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setActive(entity.isActive());
        userDto.setPhoto(entity.getPhoto());
        return userDto;
    }

}
