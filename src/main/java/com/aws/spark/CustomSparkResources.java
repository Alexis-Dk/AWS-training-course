package com.aws.spark;

import jodd.petite.meta.PetiteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.post;

@PetiteBean
public class CustomSparkResources {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomSparkResources.class);

    public static void defineResources() {

        get("/health", (req, res) -> "health 2");

        post("/webhook/shopify", (req, res) -> "shopify 2");
    }

}
