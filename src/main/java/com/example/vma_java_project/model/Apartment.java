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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "apartment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "apartment_id")
  private long apartment_id;

  @Column(name = "room_no")
  private String roomNo;
  @Column(name = "acreage")
  private double acreage;
  @Column(name = "num_of_room")
  private int numOfRoom;
  @Column(name = "status")
  private String status;
  @Column(name = "description")
  private String description;
  @Column(name = "p_email")
  private String presenter_email;
  @ManyToOne(targetEntity = Building.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "in_building", insertable = false, updatable = false)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Building building;
  @Column(name = "in_building")
  private Long building_id;

  public Apartment(String roomNo, double acreage, int numOfRoom, String status, String description,
      String presenter_email, Long building_id) {
    this.roomNo = roomNo;
    this.acreage = acreage;
    this.numOfRoom = numOfRoom;
    this.status = status;
    this.description = description;
    this.presenter_email = presenter_email;
    this.building_id = building_id;
  }
}
