package by.epam.litvinko.beautysalon.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The enum Message manager.
 */
public enum MessageManager {

    /**
     * The En en.
     */
    EN_EN( ResourceBundle.getBundle("message", new Locale("en", "EN"), ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES))),
    /**
     * The Ru ru.
     */
    RU_RU( ResourceBundle.getBundle("message", new Locale("ru", "RU")));

    private ResourceBundle bundle;

    MessageManager(ResourceBundle bundle){
        this.bundle = bundle;
    }

    /**
     * Gets message.
     *
     * @param key the key
     * @return the message
     */
    public String getMessage(String key) {
        return bundle.getString(key);
    }

}
