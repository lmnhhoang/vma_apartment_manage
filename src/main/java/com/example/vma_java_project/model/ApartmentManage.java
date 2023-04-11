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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "apartment_manage")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ApartmentManage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "aprtm_id")
  private long aprtm_id;

  @Column(name = "e_value")
  private long e_value;
  @Column(name = "w_value")
  private long w_value;
  @Column(name = "record_date", columnDefinition = "DATE")
  private LocalDate record_date;
  @Column(name = "status")
  private String status;

  @ManyToOne(targetEntity = Apartment.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "aprtm_room", insertable = false, updatable = false)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Apartment apartment;

  @Column(name = "aprtm_room")
  private Long apartment_id;

  public ApartmentManage(long e_value, long w_value, LocalDate record_date, String status,
      Long apartment_id) {
    this.e_value = e_value;
    this.w_value = w_value;
    this.record_date = record_date;
    this.status = status;
    this.apartment_id = apartment_id;
  }
}
