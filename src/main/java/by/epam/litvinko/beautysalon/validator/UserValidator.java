package by.epam.litvinko.beautysalon.validator;

import by.epam.litvinko.beautysalon.manager.PropertyManager;

public class UserValidator {

    private static final String REGEXP_PHONE_NUM = "regexp.phone_number";
    private static final String REGEXP_USERNAME = "regexp.username";
    private static final String REGEXP_USER_FIO= "regexp.user_fio";
    private static final String REGEXP_EMAIL = "regexp.email";
    private static final String REGEXP_PASSWORD = "regexp.password";
    private static final PropertyManager manager = PropertyManager.getInstance();


    public static boolean validate(String username, String firstName, String lastName, String email, String phone, String password) {
        return validateUsername(username) && validateFIO(firstName) && validateFIO(lastName) && validateEmail(email) && validatePhoneNumber(phone) && validatePassword(password);
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
