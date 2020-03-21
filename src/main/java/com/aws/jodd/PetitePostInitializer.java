package com.aws.jodd;

import com.aws.utils.amazon.S3Utils;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jodd.petite.PetiteContainer;

public class PetitePostInitializer {

    public static void init(PetiteContainer petiteContainer) {
        petiteContainer.getBean(S3Utils.class).setAmazonS3(AmazonS3ClientBuilder.defaultClient());
    }
}