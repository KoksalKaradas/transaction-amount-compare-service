package com.assignment.demo.service.impl;

import com.assignment.demo.integration.kafka.producer.KafkaProducer;
import com.assignment.demo.integration.redis.RedisQuery;
import com.assignment.demo.model.dto.RedisTransactionDto;
import com.assignment.demo.model.dto.TransactionCompareDto;
import com.assignment.demo.model.dto.TransactionDto;
import com.assignment.demo.service.TransactionService;
import com.assignment.demo.util.JsonUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final RedisQuery redisQuery;
  private final KafkaProducer kafkaProducer;

  @Override
  public void compareAmountsAndSendKafka(List<TransactionDto> transactionList) {
    List<TransactionCompareDto> transactionCompareList = compareAmounts(transactionList);
    kafkaProducer.produce(JsonUtils.serialize(transactionCompareList));
  }

  @Override
  public List<TransactionCompareDto> compareAmounts(List<TransactionDto> transactionList) {

    Set<Integer> repeated = new HashSet<>();
    transactionList.removeIf(p -> !repeated.add(p.getId()));

    List<TransactionCompareDto> transactionCompareList = new ArrayList<>();

    for (TransactionDto transaction : transactionList) {

      Object keyValue = redisQuery.getKeyValue(String.valueOf(transaction.getId()));

      if (keyValue == null || transaction.getAmount() == null) {
        continue;
      }

      RedisTransactionDto redisTransaction = JsonUtils.deserialize((String) keyValue,
          RedisTransactionDto.class);

      transactionCompareList.add(new TransactionCompareDto(transaction.getId(),
          transaction.getAmount().compareTo(redisTransaction.getAmount()) == 0));

    }

    return transactionCompareList;

  }

}
