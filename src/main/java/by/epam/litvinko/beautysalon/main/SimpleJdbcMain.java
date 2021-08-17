package by.epam.litvinko.beautysalon.main;

import by.epam.litvinko.beautysalon.util.PasswordEncryptor;

public class SimpleJdbcMain {
    public static void main(String[] args) {
        PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
        System.out.println(encryptor.getHash("admin"));

    }
}
