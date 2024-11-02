package org.learn.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static final Logger log = LoggerFactory.getLogger(PropertiesReader.class);

    private static final String DEFAULT_PATH = "config/default.properties";

    private static Properties properties;

    public static void initialize(){
        properties = loadProperties();

        for(String key:properties.stringPropertyNames())
        {
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key, System.getProperty(key));
            }
        }

        log.info("DEFAULT PROPERTIES");
        for(String key:properties.stringPropertyNames()){
            log.info("{}: {}",key, properties.getProperty(key));
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

    public static Properties loadProperties(){
        Properties properties= new Properties();
        try(InputStream stream= ResourceLoader.getResource(DEFAULT_PATH))
        {
            properties.load(stream);
        } catch (IOException e){
            log.info("unable to read properties file");
        }
        return properties;
    }

}
