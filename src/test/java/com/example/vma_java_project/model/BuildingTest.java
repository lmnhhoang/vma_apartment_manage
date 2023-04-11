package com.example.vma_java_project.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuildingTest {

  @Test
  void testEquals() {
    Building b1 = new Building("b1");
    Building b2 = new Building("b1");
    assertThat(b1).isEqualTo(b2);
  }

  @Test
  void getBuilding_name() {
    Building b1 = new Building("b1");
    assertThat(b1.getBuilding_name()).isEqualTo("b1");
  }

  @Test
  void setBuilding_id() {
    Building b1 = new Building("b1");
    b1.setBuilding_id(1);
    assertThat(b1.getBuilding_id()).isEqualTo(1);
  }

  @Test
  void setBuilding_name() {
    Building b1 = new Building("b1");
    b1.setBuilding_name("b2");
    assertThat(b1.getBuilding_name()).isEqualTo("b2");
  }
}