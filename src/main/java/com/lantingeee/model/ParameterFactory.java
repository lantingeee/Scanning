package com.lantingeee.model;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

/**
 * Created by lantingeee on 29/12/2017.
 */
public class ParameterFactory {

    private HandlerMethod handlerMethod;


    private Class<?>[] getParameterTypes() {
        MethodParameter[] parameters = getParameters();
        Class<?>[] parameterTypes = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> paramTypeClass = parameters[i].getParameterType();
            parameterTypes[i] = paramTypeClass;
        }
        return parameterTypes;
    }

    public ParameterFactory(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    private MethodParameter[] getParameters() {
        return handlerMethod.getMethodParameters();
    }

    public Parameter newParameter() {
        return new Parameter(getParameters(), handlerMethod.getMethod(), getParameterTypes());
    }

}
