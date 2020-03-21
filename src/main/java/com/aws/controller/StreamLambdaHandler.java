package com.aws.controller;

import com.aws.spark.CustomSparkResources;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import spark.Spark;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamLambdaHandler implements RequestStreamHandler {


        private static SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
        static {
        try {
            handler = SparkLambdaContainerHandler.getAwsProxyHandler();
            CustomSparkResources.defineResources();
            Spark.awaitInitialization();
        } catch (ContainerInitializationException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not initialize container", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
        throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}