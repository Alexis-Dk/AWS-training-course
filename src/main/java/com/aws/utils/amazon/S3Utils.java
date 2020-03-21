package com.aws.utils.amazon;

import com.amazonaws.services.s3.AmazonS3;
import com.aws.utils.io.InputOutputUtils;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

import java.io.IOException;

@PetiteBean
public class S3Utils {

  private AmazonS3 amazonS3;
  private InputOutputUtils inputOutputUtils;

  @PetiteInject
  public void setInputOutputUtils(InputOutputUtils inputOutputUtils) {
    this.inputOutputUtils = inputOutputUtils;
  }

  public void setAmazonS3(AmazonS3 amazonS3) {
    this.amazonS3 = amazonS3;
  }

  public boolean doesObjectExist(String bucket, String key) {
    return amazonS3.doesObjectExist(bucket, key);
  }

  public void putObject(String bucket, String key, String content) {
    amazonS3.putObject(bucket, key, content);
  }

  public void putObject(String bucket, String key, byte[] bytes) throws IOException {
    String content = inputOutputUtils.toString(bytes);
    putObject(bucket, key, content);
  }
}
