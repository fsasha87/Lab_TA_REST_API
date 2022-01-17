package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    static Properties property;

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            property = new Properties();
            property.load(fis);
        } catch (IOException e) {
            System.err.println("Properties file does not exist");
        }
    }

    public static String getHost() {
        String HOST = property.getProperty("HOST");
        return HOST;
    }

    public static String getPath() {
        String PATH = property.getProperty("PATH");
        return PATH;
    }


}
