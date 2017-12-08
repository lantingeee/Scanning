//package com.lantingeee;
//
//import com.alibaba.fastjson.JSON;
//
///**
// * Created by lantingeee on 15/10/2017.
// */
//public class MappingURLCollectionHelper {
//
//    public static String getUrlFormat(String url) {
//        return "URL :" + url;
//    }
//
//    public static String getParameter(String[] parameterNames, String[] parameterValues) {
//        StringBuilder sbu = new StringBuilder();
//        sbu.append("PARAMETER :\n");
//        for (int i = 0; i < parameterNames.length; i++) {
//            sbu.append(parameterNames[i]);
//            sbu.append(": ");
//            sbu.append(parameterValues[i]);
//        }
//        return sbu.toString();
//    }
//
//    public static String getResult(Object o) {
//        return "RESULT: " + JSON.toJSONString(o);
//    }
//}
