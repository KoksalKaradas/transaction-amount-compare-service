package com.assignment.demo.integration.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

  private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void produce(String message) {
    try {
      kafkaTemplate.send("results-topic", message);
    } catch (Exception e) {
      logger.error(String.format("KafkaProducer send error for message : %s", message), e);
      throw e;
    }
  }

}