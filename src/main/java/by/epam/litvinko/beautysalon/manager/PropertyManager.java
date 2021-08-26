package by.epam.litvinko.beautysalon.manager;

import java.util.ResourceBundle;

public class PropertyManager {
    private ResourceBundle resourceBundle;

    public PropertyManager(String path, String filename) {
        resourceBundle = ResourceBundle.getBundle(path + "." + filename);
    }
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
