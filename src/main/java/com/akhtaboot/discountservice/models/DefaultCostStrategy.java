package com.akhtaboot.discountservice.models;

import java.math.BigDecimal;

class DefaultCostStrategy implements CostStrategy {

    private double perHundredDiscount;
    private Bill bill;

    public DefaultCostStrategy(Bill bill, double perHundredDiscount) {
        this.bill = bill;
        this.perHundredDiscount = perHundredDiscount;
    }

    @Override
    public BigDecimal invoke() {
        BigDecimal result = new BigDecimal(0);
        result = addItemsAfterApplyingUserDiscount(result);
        result = applyBillDiscount(result);
        return result;
    }

    private BigDecimal addItemsAfterApplyingUserDiscount(BigDecimal result) {
        for (BillItem billItem : bill.getBillItems()) {
            result = result.add(billItem.getItem().applyDiscount(bill.getUser()));
        }
        return result;
    }

    private BigDecimal applyBillDiscount(BigDecimal amount) {
        final BigDecimal hundredsCount = amount.divideToIntegralValue(BigDecimal.valueOf(100));
        amount = amount.subtract(hundredsCount.multiply(BigDecimal.valueOf(perHundredDiscount)));
        return amount;
    }
}
