package com.assignment.demo.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaTransactionMessage {

  @JsonProperty("PID")
  Integer PID;
  @JsonProperty("PAMOUNT")
  BigDecimal PAMOUNT;
  @JsonProperty("PDATA")
  String PDATA;

}
