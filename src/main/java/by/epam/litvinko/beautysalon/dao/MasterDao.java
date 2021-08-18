package by.epam.litvinko.beautysalon.dao;
import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.Optional;

public interface MasterDao {

    Optional<Master> findMasterByUserId(Integer userId) throws DaoException;

}
