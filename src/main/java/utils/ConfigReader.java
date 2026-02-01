package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    static {
        try{
            properties=new Properties();
            FileInputStream fileInputStream=new FileInputStream("src/test/resources/config/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties file not found");
        }
    }
    public static String get(String key){
        return properties.getProperty(key);
    }


}
