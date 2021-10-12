package com.ncits.workingdays.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.IOException;
import java.util.List;

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
    public static <T> T convertToDto (Object obj, Class<T> clazz) throws IOException {
        String jsonString = objectToJsonString(obj);
        T data = jsonStringToObejct(jsonString, clazz);
        return data;
    }

    /**
     * Domain Entity List 를 Dto List로 변환
     * @param source
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> List<T> convertToDtoList(List<?> source, Class<T> clazz) throws JsonProcessingException {
        String jsonStr = objectToJsonString(source);
        List<T> dataList = jsonStringToList(jsonStr, clazz);
        return dataList;
    }

    /**
     * Json String 을 Collection<?> 형태의 Object 로 변경
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonStringToList(String jsonStr, Class<T> clazz) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            return om.readValue(jsonStr, om.getTypeFactory().constructCollectionType(List.class, clazz));
        }catch (Exception e){
            return null;
        }

    }


}
