package com.example.vma_java_project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.vma_java_project.model.Apartment;
import com.example.vma_java_project.repository.ApartmentRepository;
import com.example.vma_java_project.repository.BuildingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Sql("/createBuilding.sql")
public class ApartmentRepoTest {

  @Autowired
  ApartmentRepository repository;
  @Autowired
  BuildingRepository buildingRepository;
  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void should_find_no_aprt_if_repository_is_empty() {
    Iterable<Apartment> apartments = repository.findAll();

    assertThat(apartments).isEmpty();
  }

  @Test
  public void should_store_aprt() {

    Apartment apartment = repository.save(
        new Apartment("A1", 50.0, 3, "Empty", "A1 Room", "a1@gmail.com", 1L));

    assertThat(apartment).hasFieldOrPropertyWithValue("roomNo", "A1");
    assertThat(apartment).hasFieldOrPropertyWithValue("acreage", 50.0);
    assertThat(apartment).hasFieldOrPropertyWithValue("numOfRoom", 3);
    assertThat(apartment).hasFieldOrPropertyWithValue("status", "Empty");
    assertThat(apartment).hasFieldOrPropertyWithValue("description", "A1 Room");
    assertThat(apartment).hasFieldOrPropertyWithValue("presenter_email", "a1@gmail.com");
    assertThat(apartment).hasFieldOrPropertyWithValue("building_id", 1L);
  }

  @Test
  public void should_find_all_aprts() {

    Apartment a1 = new Apartment("A1", 50, 3, "Empty", "A1 Room", "a1@gmail.com", 1L);
    entityManager.persist(a1);

    Apartment a2 = new Apartment("A2", 60, 3, "Empty", "A2 Room", "a2@gmail.com", 1L);
    entityManager.persist(a2);

    Apartment a3 = new Apartment("A3", 70, 4, "Hired", "A3 Room", "a3@gmail.com", 1L);
    entityManager.persist(a3);

    Apartment b1 = new Apartment("B1", 70, 4, "Hired", "B1 Room", "b1@gmail.com", 2L);
    entityManager.persist(b1);

    Iterable<Apartment> apartments = repository.findAll();

    assertThat(apartments).hasSize(4).contains(a1, a2, a3, b1);
  }

  @Test
  public void should_find_aprt_by_id() {

    Apartment a1 = new Apartment("A1", 50, 3, "Empty", "A1 Room", "a1@gmail.com", 1L);
    entityManager.persist(a1);

    Apartment a2 = new Apartment("A2", 60, 3, "Empty", "A2 Room", "a2@gmail.com", 1L);
    entityManager.persist(a2);

    Apartment a3 = new Apartment("A3", 70, 4, "Hired", "A3 Room", "a3@gmail.com", 1L);
    entityManager.persist(a3);

    Apartment b1 = new Apartment("B1", 70, 4, "Hired", "B1 Room", "b1@gmail.com", 2L);
    entityManager.persist(b1);

    Apartment foundAprt = repository.findById(a2.getApartment_id()).get();

    assertThat(foundAprt).isEqualTo(a2);
  }

  @Test
  public void should_update_aprt_by_id() {

    Apartment a1 = new Apartment("A1", 50, 3, "Empty", "A1 Room", "a1@gmail.com", 1L);
    entityManager.persist(a1);

    Apartment a2 = new Apartment("A2", 60, 3, "Empty", "A2 Room", "a2@gmail.com", 1L);
    entityManager.persist(a2);

    Apartment a3 = new Apartment("A3", 70, 4, "Hired", "A3 Room", "a3@gmail.com", 1L);
    entityManager.persist(a3);

    Apartment b1 = new Apartment("B1", 70, 4, "Hired", "B1 Room", "b1@gmail.com", 2L);
    entityManager.persist(b1);

    Apartment updatedAprt = new Apartment("A1_update", 60, 2, "Hired", "A1 update Room",
        "a1@gmail.com", 1L);

    Apartment a = repository.findById(a2.getApartment_id()).get();
    a.setRoomNo(updatedAprt.getRoomNo());
    a.setAcreage(updatedAprt.getAcreage());
    a.setNumOfRoom(updatedAprt.getNumOfRoom());
    a.setStatus(updatedAprt.getStatus());
    a.setDescription(updatedAprt.getDescription());
    a.setPresenter_email(updatedAprt.getPresenter_email());
    a.setBuilding_id(updatedAprt.getBuilding_id());
    repository.save(a);

    Apartment checkA = repository.findById(a.getApartment_id()).get();

    assertThat(checkA.getApartment_id()).isEqualTo(a2.getApartment_id());
    assertThat(checkA.getRoomNo()).isEqualTo(updatedAprt.getRoomNo());
    assertThat(checkA.getAcreage()).isEqualTo(updatedAprt.getAcreage());
    assertThat(checkA.getNumOfRoom()).isEqualTo(updatedAprt.getNumOfRoom());
    assertThat(checkA.getStatus()).isEqualTo(updatedAprt.getStatus());
    assertThat(checkA.getDescription()).isEqualTo(updatedAprt.getDescription());
    assertThat(checkA.getPresenter_email()).isEqualTo(updatedAprt.getPresenter_email());
    assertThat(checkA.getBuilding_id()).isEqualTo(updatedAprt.getBuilding_id());
  }

  @Test
  public void should_delete_aprt_by_id() {

    Apartment a1 = new Apartment("A1", 50, 3, "Empty", "A1 Room", "a1@gmail.com", 1L);
    entityManager.persist(a1);

    Apartment a2 = new Apartment("A2", 60, 3, "Empty", "A2 Room", "a2@gmail.com", 1L);
    entityManager.persist(a2);

    Apartment a3 = new Apartment("A3", 70, 4, "Hired", "A3 Room", "a3@gmail.com", 1L);
    entityManager.persist(a3);

    Apartment b1 = new Apartment("B1", 70, 4, "Hired", "B1 Room", "b1@gmail.com", 2L);
    entityManager.persist(b1);

    repository.deleteById(a2.getApartment_id());

    Iterable<Apartment> apartments = repository.findAll();

    assertThat(apartments).hasSize(3).contains(a1, a3, b1);
  }

  @Test
  public void should_delete_all_aprts() {

    entityManager.persist(new Apartment("A1", 50, 3, "Empty", "A1 Room", "a1@gmail.com", 1L));
    entityManager.persist(new Apartment("A2", 60, 3, "Empty", "A2 Room", "a2@gmail.com", 1L));
    entityManager.persist(new Apartment("A3", 70, 4, "Hired", "A3 Room", "a3@gmail.com", 1L));
    entityManager.persist(new Apartment("B1", 70, 4, "Hired", "B1 Room", "b1@gmail.com", 2L));

    repository.deleteAll();

    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  public void should_count_all_aprt_in_building() {

    Apartment a1 = new Apartment("A1", 50, 3, "Empty", "A1 Room", "a1@gmail.com", 1L);
    entityManager.persist(a1);

    Apartment a2 = new Apartment("A2", 60, 3, "Empty", "A2 Room", "a2@gmail.com", 1L);
    entityManager.persist(a2);

    Apartment a3 = new Apartment("A3", 70, 4, "Hired", "A3 Room", "a3@gmail.com", 1L);
    entityManager.persist(a3);

    Apartment b1 = new Apartment("B1", 70, 4, "Hired", "B1 Room", "b1@gmail.com", 2L);
    entityManager.persist(b1);

    Double count = repository.countApartmentByInBuilding(1);

    assertThat(count).isEqualTo(3);
  }

  @Test
  public void should_find_all_aprt_in_building() {

    Apartment a1 = new Apartment("A1", 50, 3, "Empty", "A1 Room", "a1@gmail.com", 1L);
    entityManager.persist(a1);

    Apartment a2 = new Apartment("A2", 60, 3, "Empty", "A2 Room", "a2@gmail.com", 1L);
    entityManager.persist(a2);

    Apartment a3 = new Apartment("A3", 70, 4, "Hired", "A3 Room", "a3@gmail.com", 1L);
    entityManager.persist(a3);

    Apartment b1 = new Apartment("B1", 70, 4, "Hired", "B1 Room", "b1@gmail.com", 2L);
    entityManager.persist(b1);

    Iterable<Apartment> apartments = repository.findApartmentByBuildingIn(1);

    assertThat(apartments).hasSize(3);
  }

  @Test
  public void should_find_aprt_by_room() {

    Apartment a1 = new Apartment("A1", 50, 3, "Empty", "A1 Room", "a1@gmail.com", 1L);
    entityManager.persist(a1);

    Apartment a2 = new Apartment("A2", 60, 3, "Empty", "A2 Room", "a2@gmail.com", 1L);
    entityManager.persist(a2);

    Apartment a3 = new Apartment("A3", 70, 4, "Hired", "A3 Room", "a3@gmail.com", 1L);
    entityManager.persist(a3);

    Apartment b1 = new Apartment("B1", 70, 4, "Hired", "B1 Room", "b1@gmail.com", 2L);
    entityManager.persist(b1);

    Iterable<Apartment> apartments = repository.searchApartmentByRoomNo("A");

    assertThat(apartments).hasSize(3).contains(a1, a2, a3);
  }
}
