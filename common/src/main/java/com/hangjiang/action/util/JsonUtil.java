package com.hangjiang.action.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by jianghang on 2017/5/7.
 */
public class JsonUtil {

    public static String obj2Json(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static <T> T json2Object(String json, TypeReference<T> typeReference){
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(json,typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

    public static <T> T json2Object(String json,Class<T> obj){
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(json,obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

    public static void main(String[] args){

    }
}
