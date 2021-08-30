package by.epam.litvinko.beautysalon.manager;

import java.util.Locale;
import java.util.ResourceBundle;
public class PropertyManager {
    private static final PropertyManager instance = new PropertyManager();
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("regexp", Locale.getDefault());

    private PropertyManager() {
    }

    public static PropertyManager getInstance() {
        return instance;
    }

    public String getProperty(String propertyName) {
        return resourceBundle.getString(propertyName);
    }
}
