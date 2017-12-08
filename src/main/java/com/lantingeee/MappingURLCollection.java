//package com.lantingeee;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.BeanFactoryUtils;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerMapping;
//import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.*;
//
//
//@Component
//public class MappingURLCollection {
//
//
//    public static void getAllUrls() {
//
////        ServletContext servletContext = request.getSession().getServletContext();
////        WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//
//        ApplicationContext appContext = getApplicationContext();
//
//        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, HandlerMapping.class, true, false);
//        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
//
//            if (handlerMapping instanceof RequestMappingHandlerMapping) {
//                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
//                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
//
//                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
//                    RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
//                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
//
//                    final Set<String> patterns = patternsCondition.getPatterns();
//
//                    String requestUrl = patterns.stream().findFirst().orElse(StringUtils.EMPTY);
//
//                    System.out.println(requestUrl);
//                }
//            }
//        }
//    }
//
//    public void getTest() {
//
//        ApplicationContext appContext = getApplicationContext();
//
//        String[] beanNames = (
//                //this.detectHandlersInAncestorContexts ?
////                BeanFactoryUtils.beanNamesForTypeIncludingAncestors(getApplicationContext(), Object.class)
//                getApplicationContext().getBeanNamesForType(Object.class)
//        );
//
//
//        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, HandlerMapping.class, true, false);
//        // Take any bean name that we can determine URLs for.
//        for (String beanName : beanNames) {
//            String[] urls = determineUrlsForHandler(beanName);
//
//            for (int i = 0; i < urls.length; i++) {
//                System.out.println(urls[i]);
//            }
//        }
//
//    }
//
//    private String[] determineUrlsForHandler(String beanName) {
//        List<String> urls = new ArrayList<String>();
//        if (beanName.startsWith("/")) {
//            urls.add(beanName);
//        }
//        String[] aliases = getApplicationContext().getAliases(beanName);
//        for (String alias : aliases) {
//            if (alias.startsWith("/")) {
//                urls.add(alias);
//            }
//        }
//        return org.springframework.util.StringUtils.toStringArray(urls);
//    }
//
//    public static void getAllProperties() {
//        ApplicationContext appContext = getApplicationContext();
//
//        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, HandlerMapping.class, true, false);
//        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
//            if (handlerMapping instanceof RequestMappingHandlerMapping) {
//                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
//                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
//                System.out.println("---------" + handlerMethods.size());
//
//                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
//                    HandlerMethod handlerMethod = requestMappingInfoHandlerMethodEntry.getValue();
//                    String methodName = handlerMethod.getMethod().getName();
//                    int num = handlerMethod.getMethodParameters().length;
//                    for (int i = 0; i < num; i++) {
//                        System.out.println(methodName + "_" + i + "_name =");
//                        System.out.println(methodName + "_" + i + "_value =");
//                        System.out.println(methodName + "_" + i + "_desc =");
//                        System.out.println();
//                    }
//                }
//            }
//        }
//    }
//
//    public static void getAllMethodStatus() {
//
//        ApplicationContext appContext = getApplicationContext();
//        Properties prop = getFileProperties();
//
//        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, HandlerMapping.class, true, false);
//        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
//
//            if (handlerMapping instanceof RequestMappingHandlerMapping) {
//                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
//                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
//
//                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
//
//                    // get Url
//                    RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
//                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
//                    final Set<String> patterns = patternsCondition.getPatterns();
//                    String requestUrl = patterns.stream().findFirst().orElse(StringUtils.EMPTY);
//
//                    // get Parameter from properties
//
//                    HandlerMethod handlerMethod = requestMappingInfoHandlerMethodEntry.getValue();
//                    Class<?> beanType = handlerMethod.getBeanType();
//                    Object bean = handlerMethod.getBean();
//                    Method method = handlerMethod.getMethod();
//                    String methodName = method.getName();
//                    MethodParameter[] parameters = handlerMethod.getMethodParameters();
//
//                    // 参数类型 数组
//                    Class<?>[] parameterTypes = new Class[parameters.length];
//                    for (int i = 0; i < parameters.length; i++) {
//                        Class<?> paramTypeClass = parameters[i].getParameterType();
//                        parameterTypes[i] = paramTypeClass;
//                    }
//
//                    String[] parameterNames = new String[parameterTypes.length];
//                    String[] parameterValueStrings = new String[parameterTypes.length];
//                    Object[] parameterValues = new Object[parameterTypes.length];
//                    for (int i = 0; i < parameterTypes.length; i++) {
//                        String propName = methodName + "_" + i + "_name";
//                        String parameterName = prop.getProperty(propName);
//                        String parameterValueString = prop.getProperty(methodName + "_" + i + "_value");
//                        parameterNames[i] = parameterName;
//                        parameterValueStrings[i] = parameterValueString;
//                        parameterValues[i] = JSON.parseObject(parameterValueStrings[i], parameterTypes[i]);
//                    }
//
//                    Object result = null;
//                    try {
//                        result = method.invoke(beanType.cast(appContext.getBean(bean.toString())), parameterValues);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//
//                    System.out.println(getUrlFormat(requestUrl));
//                    System.out.println(getParameter(parameterNames, parameterValueStrings));
//                    System.out.println(getResult(result));
//
//                    System.out.println();
//                    System.out.println();
//                    System.out.println();
//
//                }
//            }
//        }
//    }
//
//
//    private static List<APIModel> createAPIModels() {
//        List<APIModel> result = new ArrayList<>();
//
//        ApplicationContext appContext = getApplicationContext();
//        Properties prop = getFileProperties();
//
//        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, HandlerMapping.class, true, false);
//
//        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
//
//            if (handlerMapping instanceof RequestMappingHandlerMapping) {
//                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
//                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
//                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
//
//
//                    APIModel apiModel = new APIModel();
//                    List<APIModel.Param> requestParam = new ArrayList<>();
//
//                    // get Url
//                    String URL = getAPIURL(requestMappingInfoHandlerMethodEntry);
//                    // get Parameter from properties
//                    HandlerMethod handlerMethod = requestMappingInfoHandlerMethodEntry.getValue();
//                    Class<?> beanType = handlerMethod.getBeanType();
//                    Object bean = handlerMethod.getBean();
//                    String beanTypeName = handlerMethod.getBeanType().getName();
//                    Method method = handlerMethod.getMethod();
//                    String methodName = method.getName();
//                    MethodParameter[] parameters = handlerMethod.getMethodParameters();
//
//                    if (beanTypeName.contains("dataCenter") || parameters.length == 0) {
//                        continue;
//                    }
//
//                    if (prop.getProperty(methodName + "_" + 0 + "_name") == null) {
//                        continue;
//                    }
//
//                    // 参数类型 数组
//                    Class<?>[] parameterTypes = new Class[parameters.length];
//                    for (int i = 0; i < parameters.length; i++) {
//                        Class<?> paramTypeClass = parameters[i].getParameterType();
//                        parameterTypes[i] = paramTypeClass;
//                    }
//
//                    Object[] parameterValues = new Object[parameterTypes.length];
//                    for (int i = 0; i < parameterTypes.length; i++) {
//                        String propName = methodName + "_" + i + "_name";
//                        String parameterName = prop.getProperty(propName);
//
//                        String parameterValueString = prop.getProperty(methodName + "_" + i + "_value");
//                        String parameterDesc = prop.getProperty(methodName + "_" + i + "desc");
//
//                        parameterValues[i] = JSON.parseObject(parameterValueString, parameterTypes[i]);
//
//                        APIModel.Param param = new APIModel.Param();
//                        param.setParamKey(parameterName);
//                        param.setParamName(parameterDesc);
//                        param.setParamType(getParamType(method.getParameterTypes()[i].getName()));
//
//                        requestParam.add(param);
//                    }
//
//                    Object methodResult = null;
//                    try {
//                        methodResult = method.invoke(beanType.cast(appContext.getBean(bean.toString())), parameterValues);
//                    } catch (Exception e) {
//                        apiModel.setApiFailureMock(e.toString());
//                    }
//
//                    apiModel.setGroupID(getGroupId(beanTypeName, URL));
//                    apiModel.setApiRequestParam(requestParam);
//                    apiModel.setApiURI(URL);
//                    apiModel.setApiName(URL);
//                    apiModel.setApiSuccessMock(JSON.toJSONString(methodResult));
//                    result.add(apiModel);
//                }
//
//            }
//        }
//        return result;
//    }
//
//    private static Properties getFileProperties() {
//
//        // get Parameter from properties
//        Properties prop = new Properties();//属性集合对象
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream("/Users/lantingeee/Desktop/workspace/seasun-management/code/seasun-management-backend/src/main/resources/parameters.properties");//属性文件流
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            prop.load(fis);//将属性文件流装载到Properties对象中
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return prop;
//
//    }
//
//
//    private static String getAPIURL(Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry) {
//        RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
//        PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
//        final Set<String> patterns = patternsCondition.getPatterns();
//        return patterns.stream().findFirst().orElse(StringUtils.EMPTY);
//    }
//
//
//    public static void createAPIModelsFile() {
//        MyFileUtils.writeFile("/Users/lantingeee/Desktop/workspace/seasun-management/code/seasun-management-backend/src/main/resources/data.json", JSON.toJSONString(createAPIModels()));
//    }
//}
