package com.aws.utils.stream;

import jodd.io.StreamUtil;
import jodd.petite.meta.PetiteBean;

import java.io.IOException;
import java.io.InputStream;

@PetiteBean
public class ByteStreamUtils {

  public byte[] readBytes(InputStream inputStream) throws IOException {
    return StreamUtil.readBytes(inputStream);
  }
}
