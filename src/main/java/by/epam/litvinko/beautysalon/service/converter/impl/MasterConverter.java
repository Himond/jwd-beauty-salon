package by.epam.litvinko.beautysalon.service.converter.impl;

import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.service.converter.Converter;
import by.epam.litvinko.beautysalon.service.dto.MasterDto;

public class MasterConverter implements Converter<MasterDto, Master> {

    @Override
    public MasterDto convert(Master entity) {
        MasterDto master = new MasterDto();
        master.setRole(entity.getRole());
        master.setFirstName(entity.getFirstName());
        master.setLastName(entity.getLastName());
        master.setEmail(entity.getEmail());
        master.setActive(entity.isActive());
        master.setPhoto(entity.getPhoto());
        master.setPosition(entity.getPosition());
        master.setDescription(entity.getDescription());
        return master;
    }

}
