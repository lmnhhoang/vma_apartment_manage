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
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "apartment_manage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentManage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "aprtm_id")
  private long aprtm_id;

//    @OneToMany(mappedBy = "apartment_no", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Apartment> apartmentManages;

  @Column(name = "e_value")
  private long e_value;
  @Column(name = "w_value")
  private long w_value;
  @Column(name = "last_record_date", columnDefinition = "DATE")
  private LocalDate record_date;

  @ManyToOne(targetEntity = Apartment.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "aprtm_room", insertable = false, updatable = false)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Apartment apartment;

  @Column(name = "aprtm_room")
  private Long apartment_id;

}
