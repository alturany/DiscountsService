package com.akhtaboot.discountservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private static final int OLD_CUSTOMER_YEARS = 2;

    @Id
    private Long id;
    @ManyToOne
    private UserType type;
    @Column(nullable = false)
    private LocalDate joinDate;

    public BigDecimal getDiscountPercent() {
        double result;
        final UserType type = getType();
        switch (type.getType()) {
            case EMPLOYEE:
            case AFFILIATE:
                result = type.getDiscountPercent();
                break;
            case NORMAL:
                if( getCustomerPeriod().getYears() >= OLD_CUSTOMER_YEARS) {
                    result = type.getDiscountPercent();
                    break;
                }
            default:
                result = 0.0;

        }
        return BigDecimal.valueOf(result);
    }

    private Period getCustomerPeriod() {
        return Period.between(joinDate, LocalDate.now());
    }
}
