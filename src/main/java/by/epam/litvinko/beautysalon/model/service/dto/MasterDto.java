package by.epam.litvinko.beautysalon.model.service.dto;

import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.entity.Position;
import by.epam.litvinko.beautysalon.entity.Role;

import java.io.Serializable;

/**
 * The type Master dto.
 */
public record MasterDto(int userId, int masterId, Role role, String firstName, String lastName, String email,
                        boolean isActive, String photo, Position position, String description) implements Serializable, Cloneable {

    /**
     * Create master dto.
     *
     * @param master the master
     * @return the master dto
     */
    public static MasterDto create(Master master){
        MasterDto masterDto = new MasterDto(master.getUserId(), master.getId(), master.getRole(), master.getFirstName(),
                master.getLastName(), master.getEmail(), master.isActive(), master.getPhoto(),
                master.getPosition(), master.getDescription());
        return masterDto;
    }

}
