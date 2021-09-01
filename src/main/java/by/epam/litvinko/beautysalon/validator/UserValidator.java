package by.epam.litvinko.beautysalon.validator;

import by.epam.litvinko.beautysalon.command.RequestParameter;
import by.epam.litvinko.beautysalon.manager.PropertyManager;

import java.util.Map;

public class UserValidator {

    private static final String REGEXP_PHONE_NUM = "regexp.phone_number";
    private static final String REGEXP_USERNAME = "regexp.username";
    private static final String REGEXP_USER_FIO= "regexp.user_fio";
    private static final String REGEXP_EMAIL = "regexp.email";
    private static final String REGEXP_PASSWORD = "regexp.password";
    private static final PropertyManager manager = PropertyManager.getInstance();
    private static final String EMPTY_ROW = "";

    public static boolean validate(Map<String, String> formData) {
        if (!validateUsername(formData.get(RequestParameter.USERNAME))) {
            formData.put(RequestParameter.USERNAME, EMPTY_ROW);
        }
        if (!validatePassword(formData.get(RequestParameter.PASSWORD))) {
            formData.put(RequestParameter.PASSWORD, EMPTY_ROW);
        }
        if (!validateFIO(formData.get(RequestParameter.FIRSTNAME))) {
            formData.put(RequestParameter.FIRSTNAME, EMPTY_ROW);
        }
        if (!validateFIO(formData.get(RequestParameter.LASTNAME))) {
            formData.put(RequestParameter.LASTNAME, EMPTY_ROW);
        }
        if (!validateEmail(formData.get(RequestParameter.EMAIL))) {
            formData.put(RequestParameter.EMAIL, EMPTY_ROW);
        }
        if (!validatePhoneNumber(formData.get(RequestParameter.PHONE))) {
            formData.put(RequestParameter.PHONE, EMPTY_ROW);
        }
        return !formData.containsValue(EMPTY_ROW);
    }

    public static boolean validateUsername(String username) {
        return isMatchFounded(username, manager.getProperty(REGEXP_USERNAME));
    }

    public static boolean validateFIO(String fio) {
        return isMatchFounded(fio, manager.getProperty(REGEXP_USER_FIO));
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return isMatchFounded(phoneNumber, manager.getProperty(REGEXP_PHONE_NUM));
    }

    public static boolean validateEmail(String email) {
        return isMatchFounded(email, manager.getProperty(REGEXP_EMAIL));
    }

    public static boolean validatePassword(String password) {
        return isMatchFounded(password, manager.getProperty(REGEXP_PASSWORD));
    }

    private static boolean isMatchFounded(String text, String regex) {
        return text != null && text.matches(regex);
    }
}
