package com.assignment.demo.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionCompareDto implements Serializable {

  private static final long serialVersionUID = 1L;

  Integer id;
  boolean isConsistentAmount;

}
