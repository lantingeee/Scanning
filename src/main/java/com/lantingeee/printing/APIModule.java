package com.lantingeee.printing;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lantingeee on 09/12/2017.
 */
public class APIModule {

    private String URI;

    private RequestMethod requestMethod;

    private Object[] requestParameters;

    private Object result;

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Object[] getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Object[] requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
