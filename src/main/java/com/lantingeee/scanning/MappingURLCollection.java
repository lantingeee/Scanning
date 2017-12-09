package com.lantingeee.scanning;

import com.alibaba.fastjson.JSON;
import com.lantingeee.context.ApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


import java.lang.reflect.Method;
import java.util.*;




@Component
class MappingURLCollection {

    private static ApplicationContext appContext;

    private static Collection<HandlerMapping> handlerMappings;

    MappingURLCollection(){
        appContext = ApplicationContextUtil.getApplicationContext();
        getHandlerMapping();
    }

    private void getHandlerMapping(){
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, HandlerMapping.class, true, false);
        handlerMappings = allRequestMappings.values();
    }

    private String getRequestUrl(Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry){
        RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
        PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
        final Set<String> patterns = patternsCondition.getPatterns();
        return patterns.stream().findFirst().orElse(StringUtils.EMPTY);

    }

    private Class<?>[] getParameterTypes(MethodParameter[] parameters){
        Class<?>[] parameterTypes = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> paramTypeClass = parameters[i].getParameterType();
            parameterTypes[i] = paramTypeClass;
        }
        return parameterTypes;
    }

    void getAllMethodStatus() {

        for (HandlerMapping handlerMapping : handlerMappings) {

            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {


                    HandlerMethod handlerMethod = requestMappingInfoHandlerMethodEntry.getValue();
                    MethodParameter[] parameters = handlerMethod.getMethodParameters();
                    Class<?>[] parameterTypes = getParameterTypes(parameters);

                    String[] parameterValueStrings = new String[parameterTypes.length];
                    Object[] parameterValues = new Object[parameterTypes.length];
                    for (int i = 0; i < parameterTypes.length; i++) {

                        parameterValues[i] = JSON.parseObject(parameterValueStrings[i], parameterTypes[i]);
                    }

                    Object result = null;
                    Object bean = handlerMethod.getBean();
                    Class<?> beanType = handlerMethod.getBeanType();
                    Method method = handlerMethod.getMethod();
                    try {
                        result = method.invoke(beanType.cast(appContext.getBean(bean.toString())), parameterValues);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

}
