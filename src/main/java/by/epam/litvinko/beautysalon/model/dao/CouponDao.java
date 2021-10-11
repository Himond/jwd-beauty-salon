package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Coupon;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.Optional;

/**
 * The interface Coupon dao.
 */
public interface CouponDao {

     /**
      * Find by code optional.
      *
      * @param code the code
      * @return the optional
      * @throws DaoException the dao exception
      */
     Optional<Coupon> findByCode(String code) throws DaoException;
}
