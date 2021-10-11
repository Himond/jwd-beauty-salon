package by.epam.litvinko.beautysalon.model.validator;

import java.util.Map;

/**
 * The interface Salon validator.
 */
public interface SalonValidator {

      /**
       * Validate boolean.
       *
       * @param formData the form data
       * @return the boolean
       */
      boolean validate(Map<String, String> formData);

      /**
       * Validate edit data boolean.
       *
       * @param formData the form data
       * @return the boolean
       */
      boolean validateEditData(Map<String, String> formData);

      /**
       * Validate username boolean.
       *
       * @param username the username
       * @return the boolean
       */
      boolean validateUsername(String username);

      /**
       * Validate fio boolean.
       *
       * @param fio the fio
       * @return the boolean
       */
      boolean validateFio(String fio);

      /**
       * Validate phone number boolean.
       *
       * @param phoneNumber the phone number
       * @return the boolean
       */
      boolean validatePhoneNumber(String phoneNumber);

      /**
       * Validate email boolean.
       *
       * @param email the email
       * @return the boolean
       */
      boolean validateEmail(String email);

      /**
       * Validate card number boolean.
       *
       * @param cardNumber the card number
       * @return the boolean
       */
      boolean validateCardNumber(String cardNumber);

      /**
       * Validate coupon boolean.
       *
       * @param email the email
       * @return the boolean
       */
      boolean validateCoupon(String email);

      /**
       * Validate review boolean.
       *
       * @param reviewBody the revie body
       * @return the boolean
       */
      boolean validateReview(String reviewBody);

      /**
       * Validate password boolean.
       *
       * @param code the code
       * @return the boolean
       */
      boolean validatePassword(String code);

      /**
       * Validate date boolean.
       *
       * @param date the date
       * @return the boolean
       */
      boolean validateDate(String date);
}
