package com.aws.utils.uuid;

import jodd.petite.meta.PetiteBean;

import java.util.UUID;

@PetiteBean
public class UniversallyUniqueIdentifierUtils {

  public String generate() {
    return UUID.randomUUID().toString();
  }
}
