package com.hdstrn;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {

    protected static Properties properties;

    static {
        try (FileInputStream file = new FileInputStream("src/test/resources/conf.properties");) {
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
