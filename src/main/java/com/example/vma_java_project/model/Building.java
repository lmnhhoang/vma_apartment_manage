package com.example.vma_java_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "building")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Building {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "building_id")
  private long building_id;

  @Column(name = "building_name")
  private String building_name;

  public Building(String building_name) {
    this.building_name = building_name;
  }
}
