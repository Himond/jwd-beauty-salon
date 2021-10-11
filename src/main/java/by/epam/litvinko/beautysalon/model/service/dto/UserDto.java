package by.epam.litvinko.beautysalon.model.service.dto;


import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.entity.User;

import java.io.Serializable;


/**
 * The type User dto.
 */
public record UserDto(int id, Role role, String firstName,
                      String lastName, String email, boolean isActive, String photo) implements Serializable, Cloneable {

    /**
     * Create user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public static UserDto create(User user){
        UserDto userDto = new UserDto(user.getId(), user.getRole(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.isActive(), user.getPhoto());
        return userDto;

    }

}
