package by.epam.litvinko.beautysalon.model.service.dto;

import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.entity.Position;
import by.epam.litvinko.beautysalon.entity.Role;

import java.io.Serializable;

public record MasterDto(int userId, Role role, String firstName, String lastName, String email,
                        boolean isActive, String photo, Position position, String description) implements Serializable, Cloneable {

    public static MasterDto create(Master master){
        MasterDto masterDto = new MasterDto(master.getUserId(), master.getRole(), master.getFirstName(),
                master.getLastName(), master.getEmail(), master.isActive(), master.getPhoto(),
                master.getPosition(), master.getDescription());
        return masterDto;
    }

}
