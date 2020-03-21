package com.aws.controller;

import com.aws.jodd.PetiteInitializer;
import com.aws.jodd.PetitePostInitializer;
import com.aws.spark.SparkResources;
import com.aws.spark.WebLambdaHandlerModel;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import jodd.exception.ExceptionUtil;
import jodd.petite.PetiteContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.io.InputStream;
import java.io.OutputStream;

public class WebLambdaHandler implements RequestStreamHandler {

  private static Logger LOGGER = LoggerFactory.getLogger(WebLambdaHandler.class);

  private static SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
  private static PetiteContainer petiteContainer;

  public WebLambdaHandler() {
    try {
      if (null == petiteContainer) {
        petiteContainer = PetiteInitializer.init();
        PetitePostInitializer.init(petiteContainer);
      }

      if (null == handler) {
        handler = SparkLambdaContainerHandler.getAwsProxyHandler();
        petiteContainer.getBean(SparkResources.class).defineResources();
        Spark.awaitInitialization();
      }
    } catch (ContainerInitializationException e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
      throw new RuntimeException(e);
    }
  }

  @Override
  public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {
    WebLambdaHandlerModel lambdaHandlerModel = petiteContainer.getBean(WebLambdaHandlerModel.class);
    lambdaHandlerModel.handleRequest(handler, inputStream, outputStream, context);
  }
}
