package com.assignment.demo.controller.impl;

import com.assignment.demo.controller.TransactionController;
import com.assignment.demo.mapper.TransactionMapper;
import com.assignment.demo.model.dto.TransactionCompareDto;
import com.assignment.demo.model.rest.TransactionCompareResponseModel;
import com.assignment.demo.model.rest.TransactionRequestModel;
import com.assignment.demo.service.TransactionService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController {

  private static final Logger logger = LoggerFactory.getLogger(TransactionControllerImpl.class);

  private final TransactionService service;
  private final TransactionMapper mapper;

  @Override
  public List<TransactionCompareResponseModel> compareAmounts(
      List<TransactionRequestModel> requestModel) {

    if (CollectionUtils.isEmpty(requestModel)) {
      logger.info("input transaction list is empty");
      return new ArrayList<>();
    }

    List<TransactionCompareDto> transactionCompareDtoList = service.compareAmounts(
        requestModel.stream().map(i -> mapper.transactionRequestModelToDto(i))
            .collect(Collectors.toList()));

    return transactionCompareDtoList.stream()
        .map(i -> mapper.transactionCompareDtoToResponseMode(i)).collect(Collectors.toList());

  }

}
