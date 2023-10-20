package com.assignment.demo.controller;

import com.assignment.demo.model.rest.TransactionCompareResponseModel;
import com.assignment.demo.model.rest.TransactionRequestModel;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/v1/tc")
public interface TransactionController {

  @PostMapping(value = "/compare-amounts")
  @ResponseStatus(HttpStatus.OK)
  List<TransactionCompareResponseModel> compareAmounts(
      @RequestBody List<TransactionRequestModel> requestModel);

}
