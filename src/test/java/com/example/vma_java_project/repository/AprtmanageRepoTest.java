package com.example.vma_java_project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.vma_java_project.model.ApartmentManage;
import com.example.vma_java_project.repository.AprtManageRepository;
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
public class AprtmanageRepoTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private AprtManageRepository repository;

  @Test
  public void should_find_no_manage_if_repository_is_empty() {
    Iterable<ApartmentManage> manages = repository.findAll();

    assertThat(manages).isEmpty();
  }

  @Test
  public void should_store_manage() {

    ApartmentManage manage = repository.save(
        new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.APRIL, 10), "Unchecked", 1L));

    assertThat(manage).hasFieldOrPropertyWithValue("e_value", 30L);
    assertThat(manage).hasFieldOrPropertyWithValue("w_value", 30L);
    assertThat(manage).hasFieldOrPropertyWithValue("record_date",
        LocalDate.of(2023, Month.APRIL, 10));
    assertThat(manage).hasFieldOrPropertyWithValue("status", "Unchecked");
    assertThat(manage).hasFieldOrPropertyWithValue("apartment_id", 1L);
  }

  @Test
  public void should_find_all_manages() {

    ApartmentManage m1 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 1L);
    entityManager.persist(m1);

    ApartmentManage m2 = new ApartmentManage(50L, 50L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 1L);
    entityManager.persist(m2);

    ApartmentManage m3 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 2L);
    entityManager.persist(m3);

    ApartmentManage m4 = new ApartmentManage(45L, 35L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 2L);
    entityManager.persist(m4);

    Iterable<ApartmentManage> manages = repository.findAll();

    assertThat(manages).hasSize(4).contains(m1, m2, m3, m4);
  }

  @Test
  public void should_find_manage_by_id() {

    ApartmentManage m1 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 1L);
    entityManager.persist(m1);

    ApartmentManage m2 = new ApartmentManage(50L, 50L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 1L);
    entityManager.persist(m2);

    ApartmentManage m3 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 2L);
    entityManager.persist(m3);

    ApartmentManage m4 = new ApartmentManage(45L, 35L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 2L);
    entityManager.persist(m4);

    ApartmentManage foundManage = repository.findById(m2.getAprtm_id()).get();

    assertThat(foundManage).isEqualTo(m2);
  }

  @Test
  public void should_update_manage_by_id() {

    ApartmentManage m1 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 1L);
    entityManager.persist(m1);

    ApartmentManage m2 = new ApartmentManage(50L, 50L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 1L);
    entityManager.persist(m2);

    ApartmentManage m3 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 2L);
    entityManager.persist(m3);

    ApartmentManage m4 = new ApartmentManage(45L, 35L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 2L);
    entityManager.persist(m4);

    ApartmentManage updatedManage = new ApartmentManage(35L, 55L,
        LocalDate.of(2023, Month.APRIL, 14), "Checked", 1L);

    ApartmentManage m = repository.findById(m2.getAprtm_id()).get();
    m.setE_value(updatedManage.getE_value());
    m.setW_value(updatedManage.getW_value());
    m.setRecord_date(updatedManage.getRecord_date());
    m.setStatus(updatedManage.getStatus());
    repository.save(m);

    ApartmentManage checkM = repository.findById(m.getAprtm_id()).get();

    assertThat(checkM.getAprtm_id()).isEqualTo(m2.getAprtm_id());
    assertThat(checkM.getE_value()).isEqualTo(updatedManage.getE_value());
    assertThat(checkM.getW_value()).isEqualTo(updatedManage.getW_value());
    assertThat(checkM.getRecord_date()).isEqualTo(updatedManage.getRecord_date());
    assertThat(checkM.getStatus()).isEqualTo(updatedManage.getStatus());
  }

  @Test
  public void should_delete_manage_by_id() {

    ApartmentManage m1 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 1L);
    entityManager.persist(m1);

    ApartmentManage m2 = new ApartmentManage(50L, 50L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 1L);
    entityManager.persist(m2);

    ApartmentManage m3 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 2L);
    entityManager.persist(m3);

    ApartmentManage m4 = new ApartmentManage(45L, 35L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 2L);
    entityManager.persist(m4);

    repository.deleteById(m2.getAprtm_id());

    Iterable<ApartmentManage> manages = repository.findAll();

    assertThat(manages).hasSize(3).contains(m1, m3, m4);
  }

  @Test
  public void should_delete_all_manages() {

    entityManager.persist(
        new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10), "Unchecked", 1L));
    entityManager.persist(new ApartmentManage(50L, 50L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 1L));
    entityManager.persist(new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 2L));
    entityManager.persist(new ApartmentManage(45L, 35L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 2L));

    repository.deleteAll();

    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  public void should_get_e_value_manage() {
    ApartmentManage m1 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 1L);
    entityManager.persist(m1);

    ApartmentManage m2 = new ApartmentManage(50L, 50L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 1L);
    entityManager.persist(m2);

    ApartmentManage m3 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 2L);
    entityManager.persist(m3);

    ApartmentManage m4 = new ApartmentManage(45L, 35L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 2L);
    entityManager.persist(m4);

    Long e_value = repository.getEValueByMonth(4, 1L);

    assertThat(e_value).isEqualTo(m2.getE_value());
  }

  @Test
  public void should_get_w_value_manage() {
    ApartmentManage m1 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 1L);
    entityManager.persist(m1);

    ApartmentManage m2 = new ApartmentManage(50L, 50L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 1L);
    entityManager.persist(m2);

    ApartmentManage m3 = new ApartmentManage(30L, 30L, LocalDate.of(2023, Month.MARCH, 10),
        "Unchecked", 2L);
    entityManager.persist(m3);

    ApartmentManage m4 = new ApartmentManage(45L, 35L, LocalDate.of(2023, Month.APRIL, 10),
        "Unchecked", 2L);
    entityManager.persist(m4);

    Long w_value = repository.getWValueByMonth(4, 1L);

    assertThat(w_value).isEqualTo(m2.getW_value());
  }
}
