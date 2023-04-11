package com.example.vma_java_project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.vma_java_project.model.Dwellers;
import com.example.vma_java_project.repository.DwellerRepository;
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
public class DwellerRepoTest {

  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private DwellerRepository repository;

  @Test
  public void should_find_no_people_if_repository_is_empty() {
    Iterable<Dwellers> dwellers = repository.findAll();

    assertThat(dwellers).isEmpty();
  }

  @Test
  public void should_store_people() {
    Dwellers dweller = repository.save(new Dwellers(
        100, "person1", "person1@gmail.com",
        "0123456789", LocalDate.of(2020, Month.APRIL, 11),
        "Nam", 1L));
    assertThat(dweller).hasFieldOrPropertyWithValue("cid", 100D);
    assertThat(dweller).hasFieldOrPropertyWithValue("fullname", "person1");
  }

  @Test
  public void should_find_all_people() {

    Dwellers d1 = new Dwellers(
        100, "person1", "person1@gmail.com",
        "0123456789", LocalDate.of(2020, Month.APRIL, 11),
        "Nam", 1L);
    entityManager.persist(d1);

    Dwellers d2 = new Dwellers(
        101, "person2", "person2@gmail.com",
        "0123456788", LocalDate.of(2020, Month.APRIL, 12),
        "Nam", 1L);
    entityManager.persist(d2);
    Dwellers d3 = new Dwellers(
        102, "person1", "person2@gmail.com",
        "0123456787", LocalDate.of(2020, Month.APRIL, 13),
        "Nam", 2L);
    entityManager.persist(d3);

    Iterable<Dwellers> dwellers = repository.findAll();

    assertThat(dwellers).hasSize(3).contains(d1, d2, d3);
  }

  @Test
  public void should_find_people_by_id() {

    Dwellers d1 = new Dwellers(
        100, "person1", "person1@gmail.com",
        "0123456789", LocalDate.of(2020, Month.APRIL, 11),
        "Nam", 1L);
    entityManager.persist(d1);

    Dwellers d2 = new Dwellers(
        101, "person2", "person2@gmail.com",
        "0123456788", LocalDate.of(2020, Month.APRIL, 12),
        "Nam", 1L);
    entityManager.persist(d2);
    Dwellers d3 = new Dwellers(
        102, "person1", "person2@gmail.com",
        "0123456787", LocalDate.of(2020, Month.APRIL, 13),
        "Nam", 2L);
    entityManager.persist(d3);

    Dwellers foundPerson = repository.findById(d2.getDweller_id()).get();

    assertThat(foundPerson).isEqualTo(d2);
  }

  @Test
  public void should_update_people_by_id() {
    Dwellers d1 = new Dwellers(
        100, "person1", "person1@gmail.com",
        "0123456789", LocalDate.of(2020, Month.APRIL, 11),
        "Nam", 1L);
    entityManager.persist(d1);

    Dwellers d2 = new Dwellers(
        101, "person2", "person2@gmail.com",
        "0123456788", LocalDate.of(2020, Month.APRIL, 12),
        "Nam", 1L);
    entityManager.persist(d2);
    Dwellers d3 = new Dwellers(
        102, "person1", "person2@gmail.com",
        "0123456787", LocalDate.of(2020, Month.APRIL, 13),
        "Nam", 2L);
    entityManager.persist(d3);

    Dwellers updatedPerson = new Dwellers(
        105, "personU", "personU@gmail.com",
        "0123456787", LocalDate.of(2020, Month.APRIL, 15),
        "Nữ", 1L);

    Dwellers d = repository.findById(d2.getDweller_id()).get();
    d.setCid(updatedPerson.getCid());
    d.setFullname(updatedPerson.getFullname());
    d.setEmail(updatedPerson.getEmail());
    d.setPhone(updatedPerson.getPhone());
    d.setBirthday(updatedPerson.getBirthday());
    d.setGender(updatedPerson.getGender());
    d.setApartment_id(updatedPerson.getApartment_id());
    repository.save(d);

    Dwellers checkD = repository.findById(d.getDweller_id()).get();

    assertThat(checkD.getDweller_id()).isEqualTo(d2.getDweller_id());
    assertThat(checkD.getCid()).isEqualTo(updatedPerson.getCid());
    assertThat(checkD.getFullname()).isEqualTo(updatedPerson.getFullname());
    assertThat(checkD.getEmail()).isEqualTo(updatedPerson.getEmail());
    assertThat(checkD.getPhone()).isEqualTo(updatedPerson.getPhone());
    assertThat(checkD.getBirthday()).isEqualTo(updatedPerson.getBirthday());
    assertThat(checkD.getGender()).isEqualTo(updatedPerson.getGender());
    assertThat(checkD.getApartment_id()).isEqualTo(updatedPerson.getApartment_id());
  }

  @Test
  public void should_delete_people_by_id() {

    Dwellers d1 = new Dwellers(
        100, "person1", "person1@gmail.com",
        "0123456789", LocalDate.of(2020, Month.APRIL, 11),
        "Nam", 1L);
    entityManager.persist(d1);

    Dwellers d2 = new Dwellers(
        101, "person2", "person2@gmail.com",
        "0123456788", LocalDate.of(2020, Month.APRIL, 12),
        "Nam", 1L);
    entityManager.persist(d2);
    Dwellers d3 = new Dwellers(
        102, "person1", "person2@gmail.com",
        "0123456787", LocalDate.of(2020, Month.APRIL, 13),
        "Nam", 2L);
    entityManager.persist(d3);

    repository.deleteById(d2.getDweller_id());

    Iterable<Dwellers> dwellers = repository.findAll();

    assertThat(dwellers).hasSize(2).contains(d1, d3);
  }

  @Test
  public void should_delete_all_people() {

    entityManager.persist(
        new Dwellers(
            100, "person1", "person1@gmail.com",
            "0123456789", LocalDate.of(2020, Month.APRIL, 11),
            "Nam", 1L));
    entityManager.persist(new Dwellers(
        101, "person2", "person2@gmail.com",
        "0123456788", LocalDate.of(2020, Month.APRIL, 12),
        "Nam", 1L));
    entityManager.persist(new Dwellers(
        102, "person1", "person2@gmail.com",
        "0123456787", LocalDate.of(2020, Month.APRIL, 13),
        "Nam", 2L));

    repository.deleteAll();

    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  public void should_count_all_people_in_aprt() {
    Dwellers d1 = new Dwellers(
        100, "person1", "person1@gmail.com",
        "0123456789", LocalDate.of(2020, Month.APRIL, 11),
        "Nam", 1L);
    entityManager.persist(d1);

    Dwellers d2 = new Dwellers(
        101, "person2", "person2@gmail.com",
        "0123456788", LocalDate.of(2020, Month.APRIL, 12),
        "Nam", 1L);
    entityManager.persist(d2);
    Dwellers d3 = new Dwellers(
        102, "person1", "person2@gmail.com",
        "0123456787", LocalDate.of(2020, Month.APRIL, 13),
        "Nam", 2L);
    entityManager.persist(d3);

    Double count = repository.countDwellersByApartment_id(1L);

    assertThat(count).isEqualTo(2);
  }

  @Test
  public void should_find_all_people_in_aprt() {
    Dwellers d1 = new Dwellers(
        100, "person1", "person1@gmail.com",
        "0123456789", LocalDate.of(2020, Month.APRIL, 11),
        "Nam", 1L);
    entityManager.persist(d1);

    Dwellers d2 = new Dwellers(
        101, "person2", "person2@gmail.com",
        "0123456788", LocalDate.of(2020, Month.APRIL, 12),
        "Nam", 1L);
    entityManager.persist(d2);
    Dwellers d3 = new Dwellers(
        102, "person1", "person2@gmail.com",
        "0123456787", LocalDate.of(2020, Month.APRIL, 13),
        "Nam", 2L);
    entityManager.persist(d3);

    Iterable<Dwellers> dwellers = repository.findDwellersByApartment_id(1L);

    assertThat(dwellers).hasSize(2).contains(d1, d2);
  }
}
