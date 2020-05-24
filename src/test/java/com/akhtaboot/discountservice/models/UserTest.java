package com.akhtaboot.discountservice.models;

import com.akhtaboot.discountservice.models.UserType.TypeEnum;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {

    @Test
    public void givenEmployee_whenGetDiscount_thenReturnDiscountPercent() {
        // Given
        final User employee = createUser(1L, TypeEnum.EMPLOYEE, 0.3);

        // When
        final BigDecimal discountPercent = employee.getDiscountPercent();

        // Then
        assertEquals(BigDecimal.valueOf(0.3), discountPercent);
    }


    @Test
    public void givenAffiliate_whenGetDiscount_thenReturnDiscountPercent() {
        // Given
        final User affiliate = createUser(2L, TypeEnum.AFFILIATE, 0.1);

        // When
        final BigDecimal discountPercent = affiliate.getDiscountPercent();

        // Then
        assertEquals(BigDecimal.valueOf(0.1), discountPercent);
    }


    @Test
    public void givenNormalNewUser_whenGetDiscount_thenReturnZero() {
        // Given
        final User normalNew = createUser(3L, TypeEnum.NORMAL, 0.05);

        // When
        final BigDecimal discountPercent = normalNew.getDiscountPercent();

        // Then
        assertEquals(BigDecimal.valueOf(0.0), discountPercent);
    }

    @Test
    public void givenNormalUserFor2years_whenGetDiscount_thenReturnDiscountPecent() {
        // Given
        final User normal2yearsOld = createUser(4L, TypeEnum.NORMAL, 0.05, LocalDate.now().minus(2, ChronoUnit.YEARS));

        // When
        final BigDecimal discountPercent = normal2yearsOld.getDiscountPercent();

        // Then
        assertEquals(BigDecimal.valueOf(0.05), discountPercent);
    }

    public static User createUser(long id, TypeEnum type, Double discountPercent) {
        LocalDate date = LocalDate.now();
        return createUser(id, type, discountPercent, date);
    }

    public static User createUser(long id, TypeEnum type, Double discountPercent, LocalDate joinDate) {
        return User.builder()
                .id(id)
                .type(UserType.builder()
                        .type(type)
                        .discountPercent(discountPercent)
                        .build())
                .joinDate(joinDate)
                .build();
    }
}
