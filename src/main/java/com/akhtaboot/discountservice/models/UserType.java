package com.akhtaboot.discountservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserType {
    @Id
    private Long id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    private Double discountPercent;

    enum TypeEnum {
        EMPLOYEE, AFFILIATE, NORMAL;
    }
}

