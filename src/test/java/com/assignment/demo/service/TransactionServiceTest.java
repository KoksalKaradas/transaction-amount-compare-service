package com.assignment.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.assignment.demo.integration.kafka.producer.KafkaProducer;
import com.assignment.demo.integration.redis.RedisQuery;
import com.assignment.demo.model.dto.TransactionCompareDto;
import com.assignment.demo.model.dto.TransactionDto;
import com.assignment.demo.service.impl.TransactionServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

  @Mock
  private RedisQuery redisQuery;
  @Mock
  private KafkaProducer kafkaProducer;

  private TransactionServiceImpl transactionService;

  @Before
  public void setup() {
    transactionService = new TransactionServiceImpl(redisQuery, kafkaProducer);
  }

  @Test
  public void if_input_list_has_repeated_value_compare_should_not_be_repeatable() {

    when(redisQuery.getKeyValue("2")).thenReturn(
        "{\"amount\":100.05,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");

    List<TransactionCompareDto> response = transactionService.compareAmounts(
        getTransactionDtoListWithRepeatedId());

    assertEquals(1, response.size());

  }

  @Test
  public void if_input_list_has_null_amount_compare_should_not_compare_it() {

    when(redisQuery.getKeyValue("3")).thenReturn(
        "{\"amount\":100.05,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");

    List<TransactionCompareDto> response = transactionService.compareAmounts(
        getTransactionDtoListWithNullAmount());

    assertEquals(0, response.size());

  }

  @Test
  public void compare_should_be_true() {

    when(redisQuery.getKeyValue("1")).thenReturn(
        "{\"amount\":111.11,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");
    when(redisQuery.getKeyValue("2")).thenReturn(
        "{\"amount\":222.222,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");
    when(redisQuery.getKeyValue("3")).thenReturn(
        "{\"amount\":0,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");
    when(redisQuery.getKeyValue("5")).thenReturn(
        "{\"amount\":555.55000,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");
    when(redisQuery.getKeyValue("6")).thenReturn(
        "{\"amount\":00666.66,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");
    when(redisQuery.getKeyValue("7")).thenReturn(
        "{\"amount\":77.77,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");
    when(redisQuery.getKeyValue("8")).thenReturn(
        "{\"amount\":0111,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");
    when(redisQuery.getKeyValue("9")).thenReturn(
        "{\"amount\":999.99,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");
    when(redisQuery.getKeyValue("10")).thenReturn(
        "{\"amount\":100.05,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");

    List<TransactionCompareDto> response = transactionService.compareAmounts(
        getTransactionDtoList());

    assertEquals(8, response.size());
    assertEquals(true, response.get(0).isConsistentAmount());
    assertEquals(false, response.get(1).isConsistentAmount());
    assertEquals(false, response.get(2).isConsistentAmount());
    assertEquals(true, response.get(3).isConsistentAmount());
    assertEquals(true, response.get(4).isConsistentAmount());
    assertEquals(false, response.get(5).isConsistentAmount());
    assertEquals(false, response.get(6).isConsistentAmount());
    assertEquals(true, response.get(7).isConsistentAmount());

  }

  private List<TransactionDto> getTransactionDtoListWithRepeatedId() {

    List<TransactionDto> transactionDtoList = new ArrayList<>();

    TransactionDto transactionDto = new TransactionDto();
    transactionDto.setId(2);
    transactionDto.setAmount(BigDecimal.valueOf(3344.44));
    transactionDto.setData("2222");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(2);
    transactionDto.setAmount(BigDecimal.valueOf(3344.44));
    transactionDto.setData("2222");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(2);
    transactionDto.setAmount(BigDecimal.valueOf(3344.44));
    transactionDto.setData("2222");
    transactionDtoList.add(transactionDto);

    return transactionDtoList;

  }

  private List<TransactionDto> getTransactionDtoListWithNullAmount() {

    List<TransactionDto> transactionDtoList = new ArrayList<>();

    TransactionDto transactionDto = new TransactionDto();
    transactionDto.setId(1);
    transactionDto.setAmount(BigDecimal.valueOf(544.22));
    transactionDto.setData("1111");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(2);
    transactionDto.setAmount(BigDecimal.valueOf(3344.44));
    transactionDto.setData("2222");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(3);
    transactionDto.setAmount(null);
    transactionDto.setData("3333");
    transactionDtoList.add(transactionDto);

    return transactionDtoList;

  }

  private List<TransactionDto> getTransactionDtoList() {

    List<TransactionDto> transactionDtoList = new ArrayList<>();

    TransactionDto transactionDto = new TransactionDto();
    transactionDto.setId(1);
    transactionDto.setAmount(BigDecimal.valueOf(111.11));
    transactionDto.setData("1111");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(2);
    transactionDto.setAmount(BigDecimal.valueOf(222.22));
    transactionDto.setData("2222");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(3);
    transactionDto.setAmount(BigDecimal.valueOf(333.33));
    transactionDto.setData("3333");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(4);
    transactionDto.setAmount(BigDecimal.valueOf(444.44));
    transactionDto.setData("4444");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(5);
    transactionDto.setAmount(BigDecimal.valueOf(555.55));
    transactionDto.setData("5555");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(6);
    transactionDto.setAmount(BigDecimal.valueOf(666.66));
    transactionDto.setData("6666");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(7);
    transactionDto.setAmount(BigDecimal.valueOf(777.77));
    transactionDto.setData("7777");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(8);
    transactionDto.setAmount(BigDecimal.valueOf(888.88));
    transactionDto.setData("8888");
    transactionDtoList.add(transactionDto);

    transactionDto = new TransactionDto();
    transactionDto.setId(9);
    transactionDto.setAmount(BigDecimal.valueOf(999.99));
    transactionDto.setData("9999");
    transactionDtoList.add(transactionDto);

    return transactionDtoList;

  }

}
