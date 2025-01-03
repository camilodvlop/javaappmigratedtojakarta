package co.com.application.appvalidation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    @JsonProperty("RequestId")    
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "Request [requestId=" + requestId + "]";
    }



}
