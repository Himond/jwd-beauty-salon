package by.epam.litvinko.beautysalon.model.service.dto;


import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.entity.User;

import java.io.Serializable;


public record UserDto(int id, Role role, String firstName,
                     String lastName, String email, boolean isActive, byte[] photo) implements Serializable, Cloneable {

    public static UserDto create(User user){
        UserDto userDto = new UserDto(user.getId(), user.getRole(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.isActive(), user.getPhoto());
        return userDto;

    }

}
