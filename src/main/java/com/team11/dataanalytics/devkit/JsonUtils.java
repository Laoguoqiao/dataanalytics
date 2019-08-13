package com.team11.dataanalytics.devkit;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtils {
    public static ObjectMapper objectMapper = new ObjectMapper();

    //对象转为字符串
    public static <T> String obj2String(List<T> objList){

        if(objList.size() == 0)
            return null;
        try {
            StringBuilder sb = new StringBuilder();
            for(T obj: objList) {
                sb.append(obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj));
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
