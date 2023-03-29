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
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Dwellers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dwellers implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "dweller_id")
  private long dweller_id;
  @Column(name = "cid")
  private double cid;
  @Column(name = "fullname")
  private String fullname;
  @Column(name = "email")
  private String email;
  @Column(name = "phone")
  private String phone;
  @Column(name = "birthday", columnDefinition = "DATE")
  private LocalDate birthday;
  @Column(name = "gender")
  private String gender;

  @ManyToOne(targetEntity = Apartment.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "dweller_aprtm", insertable = false, updatable = false)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Apartment apartment;
  @Column(name = "dweller_aprtm")
  private Long apartment_id;

}
