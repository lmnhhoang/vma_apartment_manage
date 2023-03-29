package com.example.vma_java_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "extra_fee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraFee implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "fee_id")
  private long fee_id;
  @Column(name = "fee_type")
  private String fee_type;

  @Column(name = "value")
  private long fee_value;

  public ExtraFee(String fee_type, long fee_value) {
    this.fee_type = fee_type;
    this.fee_value = fee_value;
  }

}
