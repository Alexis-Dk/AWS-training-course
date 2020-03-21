package com.aws.spark;

import jodd.petite.meta.PetiteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.post;

@PetiteBean
public class SparkResources {

  private static Logger LOGGER = LoggerFactory.getLogger(SparkResources.class);

  public static void defineResources() {

    get("/health", (req, res) -> "healthController.get(req, res)");

    post("/webhook/shopify", (req, res) -> "shopifyWebhookController.post(req, res)");
  }

}
