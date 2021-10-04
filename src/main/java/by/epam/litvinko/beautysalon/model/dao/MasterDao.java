package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.entity.Position;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface MasterDao {

    Optional<Master> findMasterByUserId(Integer id) throws DaoException;
    List<Position> allPosition() throws DaoException;


}
