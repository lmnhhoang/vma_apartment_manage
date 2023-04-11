package com.example.vma_java_project.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ApartmentTest {

  @Test
  void getRoomNo() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    assertThat(a1.getRoomNo()).isEqualTo("A1");
  }

  @Test
  void getAcreage() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    assertThat(a1.getAcreage()).isEqualTo(50);
  }

  @Test
  void getNumOfRoom() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    assertThat(a1.getNumOfRoom()).isEqualTo(3);
  }

  @Test
  void getStatus() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    assertThat(a1.getStatus()).isEqualTo("Empty");
  }

  @Test
  void getDescription() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    assertThat(a1.getDescription()).isEqualTo("A1 Room");
  }

  @Test
  void getPresenter_email() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    assertThat(a1.getPresenter_email()).isEqualTo("a1@gmail.com");
  }

  @Test
  void getBuilding_id() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    assertThat(a1.getBuilding_id()).isEqualTo(1L);
  }

  @Test
  void setApartment_id() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    a1.setApartment_id(2);
    assertThat(a1.getApartment_id()).isEqualTo(2);
  }

  @Test
  void setRoomNo() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    a1.setRoomNo("A2");
    assertThat(a1.getRoomNo()).isEqualTo("A2");
  }

  @Test
  void setAcreage() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    a1.setAcreage(60);
    assertThat(a1.getAcreage()).isEqualTo(60);
  }

  @Test
  void setNumOfRoom() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    a1.setNumOfRoom(2);
    assertThat(a1.getNumOfRoom()).isEqualTo(2);
  }

  @Test
  void setStatus() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    a1.setStatus("Hired");
    assertThat(a1.getStatus()).isEqualTo("Hired");
  }

  @Test
  void setDescription() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    a1.setDescription("A2 room");
    assertThat(a1.getDescription()).isEqualTo("A2 room");
  }

  @Test
  void setPresenter_email() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    a1.setPresenter_email("a2@gmail.com");
    assertThat(a1.getPresenter_email()).isEqualTo("a2@gmail.com");
  }

  @Test
  void setBuilding_id() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    a1.setBuilding_id(2L);
    assertThat(a1.getBuilding_id()).isEqualTo(2L);
  }

  @Test
  void testEquals() {
    Apartment a1 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    Apartment a2 = new Apartment("A1",
        50, 3, "Empty", "A1 Room",
        "a1@gmail.com", 1L);
    assertThat(a1).isEqualTo(a2);
  }

}