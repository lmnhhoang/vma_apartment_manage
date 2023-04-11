package com.example.vma_java_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Bill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bill_id")
  private long bill_id;

  @ManyToOne(targetEntity = Apartment.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "bill_aprt", insertable = false, updatable = false)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Apartment apartment;

  @Column(name = "bill_aprt")
  private Long apartment_id;

  @Column(name = "createdDate", columnDefinition = "DATE")
  private LocalDate createdDate;

  @Column(name = "e_fee")
  private long e_fee;

  @Column(name = "w_fee")
  private long w_fee;

  @Column(name = "ex_fee")
  private double ex_fee;

  @Column(name = "status")
  private String status;

  @Column(name = "paid_day", columnDefinition = "DATE")
  private LocalDate paid_day;

  public Bill(Long apartment_id, LocalDate createdDate, long e_fee, long w_fee, Double ex_fee,
      String status, LocalDate paid_day) {
    this.apartment_id = apartment_id;
    this.createdDate = createdDate;
    this.e_fee = e_fee;
    this.w_fee = w_fee;
    this.ex_fee = ex_fee;
    this.status = status;
    this.paid_day = paid_day;
  }
}
