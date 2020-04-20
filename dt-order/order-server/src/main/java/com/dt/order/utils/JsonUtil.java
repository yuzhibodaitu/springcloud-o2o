package com.dt.order.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json 转 javabean
     * @param jsonStr
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> T JsonToBean(String jsonStr, Class<T> objClass) {
        try {
            return objectMapper.readValue(jsonStr, objClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  json 转 List
     * @param jsonStr
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T JsonToList(String jsonStr, TypeReference typeReference) {
        try {
            return objectMapper.readValue(jsonStr, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
