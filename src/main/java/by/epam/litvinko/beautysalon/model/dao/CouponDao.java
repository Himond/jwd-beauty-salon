package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Coupon;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.Optional;

public interface CouponDao {

     Optional<Coupon> findByCode(String code) throws DaoException;
}
