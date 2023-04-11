package com.example.vma_java_project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.vma_java_project.model.Bill;
import com.example.vma_java_project.model.Dwellers;
import com.example.vma_java_project.repository.BillRepository;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SqlGroup({
    @Sql(scripts = "/createBuilding.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(scripts = "/createApartment.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)})
public class BillRepoTest {
  @Autowired
  TestEntityManager entityManager;
  @Autowired
  BillRepository repository;

  @Test
  public void should_find_no_bill_if_repository_is_empty() {
    Iterable<Bill> bills = repository.findAll();

    assertThat(bills).isEmpty();
  }
  @Test
  public void should_store_bill() {
    Bill bill = repository.save(new Bill(1L,LocalDate.of(2032,Month.APRIL,11),30L,20L,15D,"Process",null));
    assertThat(bill).hasFieldOrPropertyWithValue("apartment_id", 1L);
    assertThat(bill).hasFieldOrPropertyWithValue("createdDate", LocalDate.of(2032,Month.APRIL,11));
    assertThat(bill).hasFieldOrPropertyWithValue("e_fee", 30L);
    assertThat(bill).hasFieldOrPropertyWithValue("w_fee", 20L);
    assertThat(bill).hasFieldOrPropertyWithValue("ex_fee", 15D);
    assertThat(bill).hasFieldOrPropertyWithValue("status", "Process");
    assertThat(bill).hasFieldOrPropertyWithValue("paid_day", null);
  }

  @Test
  public void should_find_all_bill() {

    Bill b1 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b1);

    Bill b2 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b2);
    Bill b3 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b3);
    Bill b4 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b4);

    Iterable<Bill> bills = repository.findAll();

    assertThat(bills).hasSize(4).contains(b1, b2, b3,b4);
  }

  @Test
  public void should_find_bill_by_id() {

    Bill b1 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b1);

    Bill b2 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b2);
    Bill b3 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b3);
    Bill b4 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b4);

    Bill foundBill = repository.findById(b2.getBill_id()).get();

    assertThat(foundBill).isEqualTo(b2);
  }
  @Test
  public void should_update_bill_by_id() {
    Bill b1 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b1);

    Bill b2 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b2);
    Bill b3 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b3);
    Bill b4 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b4);

    Bill updatedBill = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,12),
        35L,25L,16D,"Paid",LocalDate.of(2032,Month.APRIL,15));

    Bill b = repository.findById(b2.getBill_id()).get();
    b.setApartment_id(updatedBill.getApartment_id());
    b.setCreatedDate(updatedBill.getCreatedDate());
    b.setE_fee(updatedBill.getE_fee());
    b.setW_fee(updatedBill.getW_fee());
    b.setEx_fee(updatedBill.getEx_fee());
    b.setStatus(updatedBill.getStatus());
    b.setPaid_day(updatedBill.getPaid_day());
    repository.save(b);

    Bill checkB = repository.findById(b.getBill_id()).get();

    assertThat(checkB.getBill_id()).isEqualTo(b2.getBill_id());
    assertThat(checkB.getApartment_id()).isEqualTo(updatedBill.getApartment_id());
    assertThat(checkB.getCreatedDate()).isEqualTo(updatedBill.getCreatedDate());
    assertThat(checkB.getE_fee()).isEqualTo(updatedBill.getE_fee());
    assertThat(checkB.getW_fee()).isEqualTo(updatedBill.getW_fee());
    assertThat(checkB.getEx_fee()).isEqualTo(updatedBill.getEx_fee());
    assertThat(checkB.getStatus()).isEqualTo(updatedBill.getStatus());
    assertThat(checkB.getPaid_day()).isEqualTo(updatedBill.getPaid_day());
  }
  @Test
  public void should_delete_bill_by_id() {

    Bill b1 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b1);

    Bill b2 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b2);
    Bill b3 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b3);
    Bill b4 = new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null);
    entityManager.persist(b4);

    repository.deleteById(b2.getBill_id());

    Iterable<Bill> dwellers = repository.findAll();

    assertThat(dwellers).hasSize(3).contains(b1, b3,b4);
  }
  @Test
  public void should_delete_all_people() {

    entityManager.persist(
        new Bill(1L,
            LocalDate.of(2032,Month.APRIL,11),
            30L,20L,15D,"Process",null));
    entityManager.persist(new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null));
    entityManager.persist(new Bill(1L,
        LocalDate.of(2032,Month.APRIL,11),
        30L,20L,15D,"Process",null));

    repository.deleteAll();

    assertThat(repository.findAll()).isEmpty();
  }
}
