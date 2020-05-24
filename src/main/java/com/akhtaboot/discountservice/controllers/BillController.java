package com.akhtaboot.discountservice.controllers;

import com.akhtaboot.discountservice.services.BillService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class BillController {

    //    @Autowired
    private final BillService billService;

    /**
     * Assumptions
     * 1) calculating the bill cost wouldn't update the DB even though in real project it should
     * 2) price history of items should be tracked
     * 3) discount amount should be flexible to change
     * 4) price of items before and after discount needs to be displayed
     * 5) user can be one of category employee, affiliate or regular
     */
    @GetMapping(value = "bill/cost/{billId}")
    public BigDecimal calculateBillCost(@PathVariable Long billId) {
        return billService.calculateBillCost(billId);
    }
}
