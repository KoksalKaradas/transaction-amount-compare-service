package com.assignment.demo.service;

import com.assignment.demo.model.dto.TransactionCompareDto;
import com.assignment.demo.model.dto.TransactionDto;
import java.util.List;

public interface TransactionService {

  void compareAmountsAndSendKafka(List<TransactionDto> transactionList);

  List<TransactionCompareDto> compareAmounts(List<TransactionDto> transactionList);

}
