package com.akhtaboot.discountservice.controllers;

import com.akhtaboot.discountservice.configs.GeneralExceptionHandler;
import com.akhtaboot.discountservice.services.BillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BillController.class)
@ImportAutoConfiguration(GeneralExceptionHandler.class)
public class BillControllerTest {


    @MockBean
    private BillService billService;

    @Autowired
    private BillController billController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenBillNotAvailable_whenGetBillCost_thenStatus404() throws Exception {

        // Given
        long billId = 1l;
        doThrow(new NoSuchElementException()).when(billService).calculateBillCost(billId);

        // When
        // Then
        mockMvc.perform(get("/bill/cost/"+ billId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenBillAvailable_whenGetBillCost_thenReturnCost() throws Exception {

        // Given
        long billId = 1l;
        doReturn(BigDecimal.valueOf(90.0)).when(billService).calculateBillCost(billId);

        // When
        // Then
        mockMvc.perform(get("/bill/cost/"+ billId))
                .andExpect(content().string("90.0"));
    }

}
