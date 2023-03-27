package com.example.vma_java_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private long bill_id;

    @ManyToOne
    @JoinColumn(name = "bill_aprt")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Apartment bill_aprt;

    @Column(name = "createdDate",columnDefinition = "DATE")
    private LocalDate createdDate;

    @Column(name = "e_fee")
    private long e_fee;

    @Column(name = "w_fee")
    private long w_fee;

    @Column(name = "ex_fee")
    private long ex_fee;

    @Column(name = "status")
    private String status;

    @Column(name = "paid_day",columnDefinition = "DATE")
    private LocalDate paid_day;

    public Bill(Apartment bill_aprt, LocalDate createdDate, long e_fee, long w_fee, long ex_fee, String status, LocalDate paid_day) {
        this.bill_aprt = bill_aprt;
        this.createdDate = createdDate;
        this.e_fee = e_fee;
        this.w_fee = w_fee;
        this.ex_fee = ex_fee;
        this.status = status;
        this.paid_day = paid_day;
    }
}
