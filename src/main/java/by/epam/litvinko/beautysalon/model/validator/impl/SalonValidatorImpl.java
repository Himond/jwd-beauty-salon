package by.epam.litvinko.beautysalon.model.validator.impl;

import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;
import by.epam.litvinko.beautysalon.manager.PropertyManager;
import by.epam.litvinko.beautysalon.model.validator.SalonValidator;

import java.util.Map;

public class SalonValidatorImpl implements SalonValidator {


    private static SalonValidatorImpl instance = new SalonValidatorImpl();
    private static final String REGEXP_PHONE_NUM = "regexp.phone_number";
    private static final String REGEXP_USERNAME = "regexp.username";
    private static final String REGEXP_USER_FIO= "regexp.user_fio";
    private static final String REGEXP_EMAIL = "regexp.email";
    private static final String REGEXP_COUPON = "regexp.coupon";
    private static final String REGEXP_REVIEW = "regexp.review";
    private static final String REGEXP_PASSWORD = "regexp.password";
    private static final String REGEXP_DATE = "regexp.date";
    private static final String REGEXP_CARD_NUMBER = "regexp.card_number";
    private static final PropertyManager manager = PropertyManager.getInstance();
    private static final String EMPTY_ROW = "";

    private SalonValidatorImpl() {
    }

    public static SalonValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validate(Map<String, String> formData) {
        if (!validateUsername(formData.get(USERNAME))) {
            formData.put(USERNAME, EMPTY_ROW);
        }
        if (!validatePassword(formData.get(PASSWORD))) {
            formData.put(PASSWORD, EMPTY_ROW);
        }
        validateData(formData);
        return !formData.containsValue(EMPTY_ROW);
    }

    @Override
    public boolean validateEditData(Map<String, String> formData) {
        validateData(formData);
        if (!validateDate(formData.get(BIRTHDAY))) {
            formData.put(BIRTHDAY, EMPTY_ROW);
        }
        return !formData.containsValue(EMPTY_ROW);
    }



    @Override
    public boolean validateUsername(String username) {
        return isMatchFounded(username, manager.getProperty(REGEXP_USERNAME));
    }

    @Override
    public boolean validateFio(String fio) {
        return isMatchFounded(fio, manager.getProperty(REGEXP_USER_FIO));
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return isMatchFounded(phoneNumber, manager.getProperty(REGEXP_PHONE_NUM));
    }

    @Override
    public boolean validateEmail(String email) {
        return isMatchFounded(email, manager.getProperty(REGEXP_EMAIL));
    }

    @Override
    public boolean validateCardNumber(String cardNumber) {
        return isMatchFounded(cardNumber, manager.getProperty(REGEXP_CARD_NUMBER));
    }

    @Override
    public boolean validateCoupon(String code) {
        return isMatchFounded(code, manager.getProperty(REGEXP_COUPON));
    }

    @Override
    public boolean validateReview(String reviewBody) {
        return isMatchFounded(reviewBody, manager.getProperty(REGEXP_REVIEW));
    }

    @Override
    public boolean validatePassword(String password) {
        return isMatchFounded(password, manager.getProperty(REGEXP_PASSWORD));
    }

    @Override
    public boolean validateDate(String date) {
        return isMatchFounded(date, manager.getProperty(REGEXP_DATE));
    }

    private boolean isMatchFounded(String text, String regex) {
        return text != null && text.matches(regex);
    }

    private void validateData(Map<String, String> formData) {
        if (!validateFio(formData.get(FIRSTNAME))) {
            formData.put(FIRSTNAME, EMPTY_ROW);
        }
        if (!validateFio(formData.get(LASTNAME))) {
            formData.put(LASTNAME, EMPTY_ROW);
        }
        if (!validateEmail(formData.get(EMAIL))) {
            formData.put(EMAIL, EMPTY_ROW);
        }
        if (!validatePhoneNumber(formData.get(PHONE))) {
            formData.put(PHONE, EMPTY_ROW);
        }
    }
}
