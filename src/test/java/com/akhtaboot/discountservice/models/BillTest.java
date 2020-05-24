package com.akhtaboot.discountservice.models;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class BillTest {
    @Test
    public void giveBill_whenCalculateCost_thenInvokeStrategy() {
        // Given
        final CostStrategy costStrategyMock = mock(CostStrategy.class);

        final Bill bill = Bill.builder()
                .id(1l)
                .costStrategy(costStrategyMock)
                .build();

        // When
        bill.calculateCost();

        // Then
        verify(costStrategyMock).invoke();
    }
}
