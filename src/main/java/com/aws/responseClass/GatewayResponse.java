package com.aws.responseClass;

import java.util.Map;

public class GatewayResponse {

    private String body;
    private Integer statusCode;
    private Map<String, String> headers;
    private boolean isBas64Encoded;

    public GatewayResponse(String body, Integer statusCode, Map<String, String> headers, boolean isBas64Encoded) {
        this.body = body;
        this.statusCode = statusCode;
        this.headers = headers;
        this.isBas64Encoded = isBas64Encoded;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isBas64Encoded() {
        return isBas64Encoded;
    }

    public void setBas64Encoded(boolean bas64Encoded) {
        isBas64Encoded = bas64Encoded;
    }
}
