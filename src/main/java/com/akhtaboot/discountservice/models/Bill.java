package com.akhtaboot.discountservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    private Long id;
    @Singular
    @OneToMany(mappedBy = "bill")
    private List<BillItem> billItems;
    @ManyToOne
    private User user;
    @Transient
    private CostStrategy costStrategy;

    //    @Tolerate
    public Bill() {
        costStrategy = new DefaultCostStrategy(this, 5);
    }

    public BigDecimal calculateCost() {
        return costStrategy.invoke();
    }

}
