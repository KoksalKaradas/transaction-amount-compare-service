package com.assignment.demo.model.event;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaTransactionCompareMessage implements Serializable {

  private static final long serialVersionUID = 1L;

  Integer id;
  boolean isConsistentAmount;

}
