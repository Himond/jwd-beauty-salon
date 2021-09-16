package by.epam.litvinko.beautysalon.model.validator;

import java.util.Map;

public interface SalonValidator {

      boolean validate(Map<String, String> formData);
      boolean validateEditData(Map<String, String> formData);
      boolean validateUsername(String username);
      boolean validateFio(String fio);
      boolean validatePhoneNumber(String phoneNumber);
      boolean validateEmail(String email);
      boolean validateCoupon(String email);
      boolean validatePassword(String code);
      boolean validateDate(String date);
}