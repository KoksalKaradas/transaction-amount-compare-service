package com.assignment.demo.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisTransactionDto implements Serializable {

  private static final long serialVersionUID = 1L;

  BigDecimal amount;
  MetaDataDto metadata;

  @Getter
  @Setter
  class MetaDataDto {

    Integer originatorId;
    Integer destinationId;
  }

}
