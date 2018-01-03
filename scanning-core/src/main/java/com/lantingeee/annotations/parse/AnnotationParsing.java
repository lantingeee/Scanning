package com.lantingeee.annotations.parse;

import com.alibaba.fastjson.JSON;
import com.lantingeee.annotations.ParameterValue;
import com.lantingeee.annotations.UnEnableScanning;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


/**
 * Created by lantingeee on 09/12/2017.
 */

public class AnnotationParsing {

    public static boolean unEnableScanning(Method method) {
        return method.isAnnotationPresent(UnEnableScanning.class);
    }

    public static String[] parameterValue(Method method) {
        Parameter[] parameters = method.getParameters();

        String[] parameterValues = new String[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(ParameterValue.class)) {
                ParameterValue parameterValue = parameters[i].getAnnotation(ParameterValue.class);
                parameterValues[i] = JSON.parseObject(parameterValue.value()).toJSONString();
            }
        }
        return parameterValues;
    }

}
