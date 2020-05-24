package com.akhtaboot.discountservice.services;

import com.akhtaboot.discountservice.models.Bill;
import com.akhtaboot.discountservice.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public BigDecimal calculateBillCost(Long billId) {
        final Optional<Bill> bill = billRepository.findBillAndFetchAllDetails(billId);
        return bill.map(Bill::calculateCost).orElseThrow(() -> new NoSuchElementException("No bill with Id: " + billId));
    }
}
