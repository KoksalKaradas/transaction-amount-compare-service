package com.assignment.demo.mapper;

import com.assignment.demo.model.dto.TransactionCompareDto;
import com.assignment.demo.model.dto.TransactionDto;
import com.assignment.demo.model.event.KafkaTransactionMessage;
import com.assignment.demo.model.rest.TransactionCompareResponseModel;
import com.assignment.demo.model.rest.TransactionRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class TransactionMapper {

  public abstract TransactionDto transactionRequestModelToDto(TransactionRequestModel requestModel);

  public abstract TransactionCompareResponseModel transactionCompareDtoToResponseMode(
      TransactionCompareDto transactionCompareDto);

  @Mapping(target = "id", source = "kafkaMessage.PID")
  @Mapping(target = "amount", source = "kafkaMessage.PAMOUNT")
  @Mapping(target = "data", source = "kafkaMessage.PDATA")
  public abstract TransactionDto kafkaTransactionMessageToDto(KafkaTransactionMessage kafkaMessage);

}
