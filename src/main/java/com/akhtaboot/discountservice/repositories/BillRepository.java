package com.akhtaboot.discountservice.repositories;

import com.akhtaboot.discountservice.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    /**
     * I could have used findById but this way Hibernate will generate dedicated query for each association
     * using FETCH in this query will translate into 1 query which retrieves all needed entities
     *
     * @param billId
     * @return
     */
    @Query("SELECT b FROM Bill b  "
            + "INNER JOIN FETCH b.user u "
            + "LEFT JOIN FETCH b.billItems bi "
            + "LEFT JOIN FETCH bi.item i "
            + "WHERE b.id = ?1")
    Optional<Bill> findBillAndFetchAllDetails(Long billId);

}
