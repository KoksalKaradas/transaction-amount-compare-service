package com.assignment.demo.integration.kafka.consumer;

import com.assignment.demo.mapper.TransactionMapper;
import com.assignment.demo.model.event.KafkaTransactionMessage;
import com.assignment.demo.service.TransactionService;
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

  private final TransactionService service;
  private final TransactionMapper mapper;

  @KafkaListener(topics = "transactions-topic", groupId = "assign-demo", containerFactory = "listenerContainerFactory")
  public void consume(List<KafkaTransactionMessage> message) {

    logger.info("KafkaConsumer gets the message : {}", message);

    service.compareAmountsAndSendKafka(
        message.stream().map(i -> mapper.kafkaTransactionMessageToDto(i))
            .collect(Collectors.toList()));

  }

}


