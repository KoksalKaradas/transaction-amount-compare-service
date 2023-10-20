package com.assignment.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.assignment.demo.controller.impl.TransactionControllerImpl;
import com.assignment.demo.mapper.TransactionMapper;
import com.assignment.demo.model.rest.TransactionCompareResponseModel;
import com.assignment.demo.service.TransactionService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

  @Mock
  private TransactionService service;
  @Mock
  private TransactionMapper mapper;

  private TransactionControllerImpl transactionController;

  @Before
  public void setup() {
    transactionController = new TransactionControllerImpl(service, mapper);
  }

  @Test
  public void if_input_list_is_null_compare_response_should_be_empty() {
    List<TransactionCompareResponseModel> response = transactionController.compareAmounts(null);
    assertEquals(0, response.size());
  }

  @Test
  public void if_input_list_is_empty_compare_response_should_be_empty() {
    List<TransactionCompareResponseModel> response = transactionController.compareAmounts(
        new ArrayList<>());
    assertEquals(0, response.size());
  }

  @Test
  public void if_input_list_is_empty_compare_service_should_not_be_called() {
    List<TransactionCompareResponseModel> response = transactionController.compareAmounts(
        new ArrayList<>());
    verify(service, times(0)).compareAmounts(new ArrayList<>());
  }

}
