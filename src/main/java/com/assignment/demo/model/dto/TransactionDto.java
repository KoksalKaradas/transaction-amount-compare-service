package com.assignment.demo.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto implements Serializable {

  private static final long serialVersionUID = 1L;

  Integer id;
  BigDecimal amount;
  String data;

}
