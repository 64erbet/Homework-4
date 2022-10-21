package ua.goit.jdbc.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
    public Properties loadProperties() {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("application.properties");) {
            Properties properties = new Properties();
            properties.load(stream);
            return properties;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
