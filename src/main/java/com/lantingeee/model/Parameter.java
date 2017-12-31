package com.lantingeee.model;

import com.alibaba.fastjson.JSON;
import com.lantingeee.annotations.parse.AnnotationParsing;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;

/**
 * Created by lantingeee on 29/12/2017.
 */
public class Parameter {

    private MethodParameter[] parameters;

    private Method method;

    private Class<?>[] parameterTypes;

    public Class<?>[] getParameterType() {
        return this.parameterTypes;
    }


    public Object[] getParameterValues() {

        String[] parameterValueStrings = AnnotationParsing.parameterValue(method);
        Object[] parameterValues = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameterValues[i] = JSON.parseObject(parameterValueStrings[i], parameterTypes[i]);
        }
        return parameterValues;
    }

    public MethodParameter[] getParameters() {
        return parameters;
    }

    public Method getMethod() {
        return method;
    }

    Parameter(MethodParameter[] parameters, Method method, Class<?>[] parameterTypes) {
        this.parameters = parameters;
        this.method = method;
        this.parameterTypes = parameterTypes;
    }
}
