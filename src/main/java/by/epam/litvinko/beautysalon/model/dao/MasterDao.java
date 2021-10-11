package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.entity.Position;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;

import java.util.List;
import java.util.Optional;

/**
 * The interface Master dao.
 */
public interface MasterDao {

    /**
     * Find master by user id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Master> findMasterByUserId(Integer id) throws DaoException;

    /**
     * All position list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Position> allPosition() throws DaoException;


}
