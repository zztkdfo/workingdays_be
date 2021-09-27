package com.ncits.workingdays.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class XJsonUtils {

    /**
     * Convert Object to JSON String
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String objectToJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Convert JSON to Object
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T jsonStringToObejct(String jsonString ,Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // 모르는 property에 대해 무시하고 넘어간다.
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * Convert Object to Vo
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T convertToVo (Object obj, Class<T> clazz) throws IOException {
        String jsonString = objectToJsonString(obj);
        T data = jsonStringToObejct(jsonString, clazz);
        return data;
    }


}
