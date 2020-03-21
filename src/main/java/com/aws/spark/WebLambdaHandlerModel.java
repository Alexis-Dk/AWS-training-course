package com.aws.spark;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.aws.utils.stream.ByteStreamUtils;
import com.aws.utils.uuid.UniversallyUniqueIdentifierUtils;
import jodd.exception.ExceptionUtil;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@PetiteBean
public class WebLambdaHandlerModel {

  private static Logger LOGGER = LoggerFactory.getLogger(WebLambdaHandlerModel.class);

  // private S3Dumper s3Dumper;
  private UniversallyUniqueIdentifierUtils universallyUniqueIdentifierUtils;
  private ByteStreamUtils byteStreamUtils;

  @PetiteInject
  public void setByteStreamUtils(ByteStreamUtils byteStreamUtils) {
    this.byteStreamUtils = byteStreamUtils;
  }

  @PetiteInject
  public void setUniversallyUniqueIdentifierUtils(UniversallyUniqueIdentifierUtils universallyUniqueIdentifierUtils) {
    this.universallyUniqueIdentifierUtils = universallyUniqueIdentifierUtils;
  }

  /*
  @PetiteInject
  public void setS3Dumper(S3Dumper s3Dumper) {
    this.s3Dumper = s3Dumper;
  }
   */

  public void handleRequest(
      SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler,
      InputStream inputStream,
      OutputStream outputStream,
      Context context
  ) {
    try {
      String uuid = universallyUniqueIdentifierUtils.generate();

      //****

      byte[] inputBytes = byteStreamUtils.readBytes(inputStream);
      //s3Dumper.dump(uuid, Constants.SUBFOLDER_WEB_REQUESTS_NAME, inputBytes);

      //****

      ByteArrayInputStream bais = new ByteArrayInputStream(inputBytes);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      handler.proxyStream(bais, baos, context);

      //****

      byte[] outputBytes = baos.toByteArray();
      //s3Dumper.dump(uuid, Constants.SUBFOLDER_WEB_RESPONSES_NAME, outputBytes);

      //****

      outputStream.write(outputBytes);
      outputStream.close();
    } catch (Exception e) {
      LOGGER.error(ExceptionUtil.exceptionStackTraceToString(e));
    }
  }
}
