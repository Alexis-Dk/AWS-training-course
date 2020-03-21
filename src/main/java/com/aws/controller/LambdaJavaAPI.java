package com.aws.controller;

import com.aws.requestClass.RequestClass;
import com.aws.responseClass.GatewayResponse;
import com.aws.service.dagger.component.DaggerServiceComponent;
import com.aws.service.dagger.component.ServiceComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Collections;

public class LambdaJavaAPI implements RequestHandler<RequestClass, GatewayResponse> {

    private static final String GREETING_PATTERN = "Hello %s, %s. Time is ";
    private static final String X_POWERED_BY = "X-Powered-By";
    private static final String COMPANY_NAME = "MyCompany";

    private final ServiceComponent component;

    public LambdaJavaAPI(){
        component = DaggerServiceComponent.builder().build();
    }

    @Override
    public GatewayResponse handleRequest(RequestClass request, Context context) {
        String message = String.format(GREETING_PATTERN, request.getFirstName(), request.getLastName());
        System.out.println(message);

        message += component.aggregate().getTime();

        GatewayResponse gatewayResponse = new GatewayResponse(
            message,
            200,
            Collections.singletonMap(X_POWERED_BY, COMPANY_NAME),
            false
        );
        return gatewayResponse;
    }
}
