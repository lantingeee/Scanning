package com.lantingeee.scanning;

import com.lantingeee.annotations.parse.AnnotationParsing;
import com.lantingeee.context.ApplicationContextUtil;
import com.lantingeee.model.Parameter;
import com.lantingeee.model.ParameterFactory;
import com.lantingeee.model.RequestFactory;
import com.lantingeee.model.RequestURI;
import com.lantingeee.printing.APIModule;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Component
class MappingURLCollection {

    private static ApplicationContext appContext;

    private static Collection<HandlerMapping> handlerMappings;

    private AnnotationParsing annotationParsing = new AnnotationParsing();

    MappingURLCollection() {
        appContext = ApplicationContextUtil.getApplicationContext();
        getHandlerMapping();
    }

    private void getHandlerMapping() {
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, HandlerMapping.class, true, false);
        handlerMappings = allRequestMappings.values();
    }


    List<APIModule> getAllMethodStatus() {
        List<APIModule> apiModules = new ArrayList<APIModule>();

        for (HandlerMapping handlerMapping : handlerMappings) {

            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {


                    HandlerMethod handlerMethod = requestMappingInfoHandlerMethodEntry.getValue();
                    Parameter parameter = new ParameterFactory(handlerMethod).newParameter();
                    Method method = parameter.getMethod();
                    Object[] parameterValues = parameter.getParameterType();

                    RequestFactory requestFactory = new RequestFactory(requestMappingInfoHandlerMethodEntry);
                    com.lantingeee.model.RequestMethod requestMethod = requestFactory.newRequestMethod();
                    RequestURI requestURI = requestFactory.newRequestURI();

                    if (AnnotationParsing.unEnableScanning(method))
                        continue;

                    Object result = null;
                    try {
                        result = method.invoke(handlerMethod.getBeanType().cast(appContext.getBean(handlerMethod.getBean().toString())), parameterValues);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    apiModules.add(new APIModule(requestURI.getRequestUrl(), requestMethod.getRequestMethod(),
                            parameterValues, result));
                }
            }
        }
        return apiModules;
    }

}
