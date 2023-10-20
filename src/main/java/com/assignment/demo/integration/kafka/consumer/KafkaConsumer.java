package com.assignment.demo.integration.kafka.consumer;

import com.assignment.demo.mapper.TransactionMapper;
import com.assignment.demo.model.event.KafkaTransactionMessage;
import com.assignment.demo.service.TransactionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

  private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

  private static Gson gson;
  private static GsonBuilder gsonBuilder = new GsonBuilder();

  static {
    gson = gsonBuilder.create();
  }

  private final TransactionService service;
  private final TransactionMapper mapper;

  @KafkaListener(topics = "transactions-topic", groupId = "assign-demo")
  public void consume(String message) {

    logger.info("KafkaConsumer gets the message : {}", message);

    List<KafkaTransactionMessage> kafkaTransactionMessageList = deserialize(message);

    service.compareAmountsAndSendKafka(
        kafkaTransactionMessageList.stream().map(i -> mapper.kafkaTransactionMessageToDto(i))
            .collect(Collectors.toList()));

  }

  private <T> List<T> deserialize(String message) {
    Type listOfObject = new TypeToken<ArrayList<KafkaTransactionMessage>>() {
    }.getType();
    return gson.fromJson(message, listOfObject);
  }

}


