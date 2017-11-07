package com.hangjiang.action.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianghang on 2017/11/2.
 */
public class ClassUtil {

    private static Map classMap = new HashMap();

    static{
        classMap.put("int",Integer.class);
        classMap.put("boolean",Boolean.class);
    }

    public static <T> Class<T> getTypeClass(String s){
        return (Class<T>) classMap.get(s);
    }

    public static Object getValue(String type,String value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getTypeClass(type).getMethod("valueOf",String.class);
        Object obj = method.invoke(null,value);

        return obj;
    }

    public static void main(String[] args) throws Exception {
        Integer b = (Integer) getValue("int","2");
        System.out.println(b);
    }
}
