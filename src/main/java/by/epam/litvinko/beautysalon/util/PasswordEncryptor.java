package by.epam.litvinko.beautysalon.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 * The type Password encryptor.
 */
public class PasswordEncryptor {
    private static final PasswordEncryptor instance = new PasswordEncryptor();

    private PasswordEncryptor() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static PasswordEncryptor getInstance() {
        return instance;
    }

    /**
     * Check hash boolean.
     *
     * @param password the password
     * @param hash     the hash
     * @return the boolean
     */
    public boolean checkHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    /**
     * Gets hash.
     *
     * @param password the password
     * @return the hash
     */
    public String getHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    /**
     * Generate random password string.
     *
     * @return the string
     */
    public static String generateRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(12).toUpperCase();
    }
}
