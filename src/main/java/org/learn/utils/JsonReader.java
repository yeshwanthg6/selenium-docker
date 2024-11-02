package org.learn.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JsonReader {
    private static final Logger log = LoggerFactory.getLogger(JsonReader.class);
    private static final ObjectMapper mapper=new ObjectMapper();

    public static <T> T getJsonData(String path,Class<T> type){
        try(InputStream stream = ResourceLoader.getResource(path))
        {
            return mapper.readValue(stream, type);
        }catch (IOException e){
            log.error("Unable to read data from {}",path,e);
        }
        return null;
    }
}
