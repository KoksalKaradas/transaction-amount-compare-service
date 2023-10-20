package com.assignment.demo.model.event;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaTransactionMessage implements Serializable {

  private static final long serialVersionUID = 1L;

  Integer PID;
  BigDecimal PAMOUNT;
  String PDATA;

}
