package com.akhtaboot.discountservice.models;

import java.math.BigDecimal;

public interface CostStrategy {
    BigDecimal invoke();
}
