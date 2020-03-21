package com.aws.utils.io;

import jodd.petite.meta.PetiteBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@PetiteBean
public class InputOutputUtils {

  public String toString(byte[] bytes) throws IOException {
    return org.apache.commons.io.IOUtils.toString(bytes, StandardCharsets.UTF_8.name());
  }

  public byte[] toByteArray(InputStream inputStream) throws IOException {
    return org.apache.commons.io.IOUtils.toByteArray(inputStream);
  }

  public void write(byte[] bytes, OutputStream outputStream) throws IOException {
    org.apache.commons.io.IOUtils.write(bytes, outputStream);
  }
}
