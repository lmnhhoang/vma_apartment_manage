package com.example.vma_java_project.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;

class BillTest {

  @Test
  void testEquals() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    Bill b2 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    assertThat(b1).isEqualTo(b2);
  }

  @Test
  void getApartment_id() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    assertThat(b1.getApartment_id()).isEqualTo(1L);
  }

  @Test
  void getCreatedDate() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2023, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    assertThat(b1.getCreatedDate()).isEqualTo(LocalDate.of(2023,Month.APRIL,11));
  }

  @Test
  void getE_fee() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    assertThat(b1.getE_fee()).isEqualTo(30L);
  }

  @Test
  void getW_fee() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    assertThat(b1.getW_fee()).isEqualTo(20L);
  }

  @Test
  void getEx_fee() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    assertThat(b1.getEx_fee()).isEqualTo(15D);
  }

  @Test
  void getStatus() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    assertThat(b1.getStatus()).isEqualTo("Process");
  }

  @Test
  void getPaid_day() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",LocalDate.of(2032, Month.APRIL,15));
    assertThat(b1.getPaid_day()).isEqualTo(LocalDate.of(2032, Month.APRIL,15));
  }

  @Test
  void setBill_id() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    b1.setBill_id(1);
    assertThat(b1.getBill_id()).isEqualTo(1);
  }

  @Test
  void setApartment_id() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    b1.setApartment_id(2L);
    assertThat(b1.getApartment_id()).isEqualTo(2L);
  }

  @Test
  void setCreatedDate() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    b1.setCreatedDate(LocalDate.of(2032, Month.APRIL,12));
    assertThat(b1.getCreatedDate()).isEqualTo(LocalDate.of(2032, Month.APRIL,12));
  }

  @Test
  void setE_fee() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    b1.setE_fee(31L);
    assertThat(b1.getE_fee()).isEqualTo(31L);
  }

  @Test
  void setW_fee() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    b1.setW_fee(31L);
    assertThat(b1.getW_fee()).isEqualTo(31L);
  }

  @Test
  void setEx_fee() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    b1.setEx_fee(31L);
    assertThat(b1.getEx_fee()).isEqualTo(31L);
  }

  @Test
  void setStatus() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    b1.setStatus("Paid");
    assertThat(b1.getStatus()).isEqualTo("Paid");
  }

  @Test
  void setPaid_day() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032, Month.APRIL,11),
        30L,20L,15D,"Process",null);
    b1.setPaid_day(LocalDate.of(2032, Month.APRIL,15));
    assertThat(b1.getPaid_day()).isEqualTo(LocalDate.of(2032, Month.APRIL,15));
  }
}