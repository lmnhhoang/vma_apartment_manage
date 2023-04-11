package com.example.vma_java_project.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;

class ApartmentManageTest {

  @Test
  void testEquals() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    ApartmentManage a2 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    assertThat(a1).isEqualTo(a2);
  }

  @Test
  void getE_value() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    assertThat(a1.getE_value()).isEqualTo(35L);
  }

  @Test
  void getW_value() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    assertThat(a1.getW_value()).isEqualTo(55L);
  }

  @Test
  void getRecord_date() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    assertThat(a1.getRecord_date()).isEqualTo(LocalDate.of(2023, Month.APRIL, 14));
  }

  @Test
  void getStatus() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    assertThat(a1.getStatus()).isEqualTo("Checked");
  }


  @Test
  void getApartment_id() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    assertThat(a1.getApartment_id()).isEqualTo(1L);
  }

  @Test
  void setAprtm_id() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    a1.setAprtm_id(1);
    assertThat(a1.getAprtm_id()).isEqualTo(1);
  }

  @Test
  void setE_value() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    a1.setE_value(40L);
    assertThat(a1.getE_value()).isEqualTo(40L);
  }

  @Test
  void setW_value() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    a1.setW_value(40L);
    assertThat(a1.getW_value()).isEqualTo(40L);
  }

  @Test
  void setRecord_date() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    a1.setRecord_date(LocalDate.of(2023,Month.APRIL,15));
    assertThat(a1.getRecord_date()).isEqualTo(LocalDate.of(2023,Month.APRIL,15));
  }

  @Test
  void setStatus() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    a1.setStatus("Uncheck");
    assertThat(a1.getStatus()).isEqualTo("Uncheck");
  }

  @Test
  void setApartment_id() {
    ApartmentManage a1 = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);
    a1.setApartment_id(2L);
    assertThat(a1.getApartment_id()).isEqualTo(2L);
  }

}