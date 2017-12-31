package com.lantingeee.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.Map;
import java.util.Set;

/**
 * Created by lantingeee on 31/12/2017.
 */
public class RequestFactory {

    private Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry;

    public RequestFactory(Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry) {
        this.requestMappingInfoHandlerMethodEntry = requestMappingInfoHandlerMethodEntry;
    }

    public RequestURI newRequestURI() {
        return new DefaultRequestURI();
    }

    public RequestMethod newRequestMethod() {
        return new DefaultRequestMethod();
    }


    private class DefaultRequestURI implements RequestURI {
        @Override
        public String getRequestUrl() {
            RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            final Set<String> patterns = patternsCondition.getPatterns();
            return patterns.stream().findFirst().orElse(StringUtils.EMPTY);
        }

        private DefaultRequestURI() {
        }
    }

    private class DefaultRequestMethod implements RequestMethod {

        @Override
        public org.springframework.web.bind.annotation.RequestMethod getRequestMethod() {
            RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            final Set<org.springframework.web.bind.annotation.RequestMethod> methods = methodsCondition.getMethods();
            return methods.stream().findFirst().orElse(null);
        }

        private DefaultRequestMethod() {
        }
    }

}
