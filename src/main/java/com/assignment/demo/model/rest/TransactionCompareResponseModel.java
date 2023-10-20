package com.assignment.demo.model.rest;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionCompareResponseModel implements Serializable {

  private static final long serialVersionUID = 1L;

  Integer id;
  boolean isConsistentAmount;

}
