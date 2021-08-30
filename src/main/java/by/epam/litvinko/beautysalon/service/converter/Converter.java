package by.epam.litvinko.beautysalon.service.converter;

import by.epam.litvinko.beautysalon.entity.Entity;
import by.epam.litvinko.beautysalon.service.dto.AbstractDto;

public interface Converter<D extends AbstractDto, E extends Entity> {

    D convert(E entity);

}
