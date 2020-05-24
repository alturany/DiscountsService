package com.akhtaboot.discountservice.services;

import com.akhtaboot.discountservice.models.Bill;
import com.akhtaboot.discountservice.repositories.BillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BillService.class)
public class BillServiceTest {
    @Autowired
    private BillService billService;

    @MockBean
    private BillRepository billRepository;

    @Test
    public void givenBillWithCostOf120_whenCalculateBillCost_thenReturnTheBillCost() {

        //Given
        final long billId = 1l;
        final BigDecimal billCost = BigDecimal.valueOf(120.0);
        final Bill billMock = mock(Bill.class);
        doReturn(billCost).when(billMock).calculateCost();
        doReturn(Optional.of(billMock)).when(billRepository).findBillAndFetchAllDetails(billId);

        //When
        final BigDecimal result = billService.calculateBillCost(billId);

        //Then
        assertEquals(result, billCost);
    }

    @Test
    public void givenNotExistingBill_whenCalculateBillCost_thenThrowNoSuchElementException() {

        //Given
        final long billId = -1l;
        doReturn(Optional.empty()).when(billRepository).findBillAndFetchAllDetails(billId);
        //Then
        //When
        assertThrows(NoSuchElementException.class, () -> {
            billService.calculateBillCost(billId);
        }, "No bill with Id: " + billId);


    }

}
