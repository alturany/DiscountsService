package com.akhtaboot.discountservice.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


public class ItemTest {

    @Test
    public void givenGroceryItem_whenApplyDiscount_thenDontChangePrice() {
        // Given
        final User empMock = createMockUser(0.3);

        final BigDecimal price = BigDecimal.valueOf(12);
        final Item groceryItem = createItem(price, ItemCategory.GROCERIES);

        // When
        final BigDecimal result = groceryItem.applyDiscount(empMock);

        // Then
        assertEquals(price, result);
    }

    @Test
    public void givenNONGroceryItem_whenApplyDiscount_thenReduceThePriceByDiscount() {
        // Given
        final User empMock = createMockUser(0.3);

        final BigDecimal price = BigDecimal.valueOf(24.99);
        final Item groceryItem = createItem(price, ItemCategory.CLOTHING);

        // When
        final BigDecimal result = groceryItem.applyDiscount(empMock);

        // Then
        assertNotEquals(price, result);
        assertEquals(BigDecimal.valueOf(17.49), result);
    }

    public Item createItem(BigDecimal price, ItemCategory itemCategory) {
        return Item.builder()
                .id(1L)
                .category(itemCategory)
                .price(price)
                .build();
    }

    public static User createMockUser(double discount) {
        final User empMock = mock(User.class);
        doReturn(BigDecimal.valueOf(discount)).when(empMock).getDiscountPercent();
        return empMock;
    }
}
