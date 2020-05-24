package com.akhtaboot.discountservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    private Long id;

    @Column(nullable = false)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemCategory category;

    @OneToMany(mappedBy = "item")
    private List<BillItem> billItems;

    public BigDecimal applyDiscount(User user) {
        if (getCategory() == ItemCategory.GROCERIES) return getPrice();

        BigDecimal discountPercent = user.getDiscountPercent();
        final BigDecimal discountAmount = getPrice().multiply(discountPercent)
                .setScale(2, RoundingMode.HALF_DOWN);
        return getPrice().subtract(discountAmount);
    }
}


