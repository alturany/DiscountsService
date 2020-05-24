package com.akhtaboot.discountservice.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class DefaultCostStrategyTest {
    @Test
    public void givenBillWithTwoDiscountedItemsLessThan100_whenInvokeCostStrategy_thenReturnTheSumOf2DiscountedItems() {
        // Given
        final User userMock = ItemTest.createMockUser(0.3);
        final ArrayList<BillItem> billItems = new ArrayList<>();

        addBillItemMock(userMock, billItems, 12);
        addBillItemMock(userMock, billItems, 13);
        final Bill billMock = createBillMock(userMock, billItems);

        DefaultCostStrategy defaultCostStrategy = new DefaultCostStrategy(billMock, 5);

        // When
        final BigDecimal result = defaultCostStrategy.invoke();

        // Then
        assertEquals(BigDecimal.valueOf(25.0), result);
    }

    @Test
    public void givenBillWithTwoDiscountedItemsOf170_whenInvokeCostStrategy_thenReturnTheSumOf2DiscountedItemsAndDiscount5() {
        // Given
        final User userMock = ItemTest.createMockUser(0.3);
        final ArrayList<BillItem> billItems = new ArrayList<>();

        addBillItemMock(userMock, billItems, 120);
        addBillItemMock(userMock, billItems, 50);
        final Bill billMock = createBillMock(userMock, billItems);

        DefaultCostStrategy defaultCostStrategy = new DefaultCostStrategy(billMock, 5);

        // When
        final BigDecimal result = defaultCostStrategy.invoke();

        // Then
        assertEquals(BigDecimal.valueOf(165.0), result);
    }

    private Bill createBillMock(User userMock, ArrayList<BillItem> billItems) {
        final Bill billMock = mock(Bill.class);
        doReturn(userMock).when(billMock).getUser();
        doReturn(billItems).when(billMock).getBillItems();
        return billMock;
    }

    private void addBillItemMock(User userMock, List<BillItem> billItems, int discountedPrice) {
        final Item itemMock = mock(Item.class);
        doReturn(BigDecimal.valueOf(discountedPrice)).when(itemMock).applyDiscount(userMock);
        final BillItem billItemMock = mock(BillItem.class);
        doReturn(itemMock).when(billItemMock).getItem();
        billItems.add(billItemMock);
    }
}
