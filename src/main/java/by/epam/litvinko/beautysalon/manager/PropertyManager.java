package by.epam.litvinko.beautysalon.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Property manager.
 */
public class PropertyManager {
    private static final PropertyManager instance = new PropertyManager();
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("regexp", Locale.getDefault());

    private PropertyManager() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static PropertyManager getInstance() {
        return instance;
    }

    /**
     * Gets property.
     *
     * @param propertyName the property name
     * @return the property
     */
    public String getProperty(String propertyName) {
        return resourceBundle.getString(propertyName);
    }
}
