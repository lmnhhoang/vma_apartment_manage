package com.example.vma_java_project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.vma_java_project.model.Building;
import com.example.vma_java_project.repository.BuildingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BuildingRepoTest {

  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private BuildingRepository repository;

  @Test
  public void should_find_no_user_if_repository_is_empty() {
    Iterable<Building> buildings = repository.findAll();

    assertThat(buildings).isEmpty();
  }

  @Test
  public void should_store_building() {
    Building building = repository.save(new Building("building 1"));

    assertThat(building).hasFieldOrPropertyWithValue("building_name", "building 1");
  }

  @Test
  public void should_find_all_buildings() {
    Building b1 = new Building("building 1");
    entityManager.persist(b1);

    Building b2 = new Building("building 2");
    entityManager.persist(b2);

    Building b3 = new Building("building 3");
    entityManager.persist(b3);

    Iterable<Building> buildings = repository.findAll();

    assertThat(buildings).hasSize(3).contains(b1, b2, b3);
  }

  @Test
  public void should_find_building_by_id() {
    Building b1 = new Building("building 1");
    entityManager.persist(b1);

    Building b2 = new Building("building 2");
    entityManager.persist(b2);

    Building foundBuilding = repository.findById(b2.getBuilding_id()).get();

    assertThat(foundBuilding).isEqualTo(b2);
  }

  @Test
  public void should_find_buildings_by_name_containing_string() {
    Building b1 = new Building("building 1");
    entityManager.persist(b1);

    Building b2 = new Building("building 2");
    entityManager.persist(b2);

    Building b3 = new Building("build=ting 3");
    entityManager.persist(b3);

    Iterable buildings = repository.findByBuilding_nameContaining("uild");

    assertThat(buildings).hasSize(3).contains(b1, b2);
  }

  @Test
  public void should_update_building_by_id() {
    Building b1 = new Building("building 1");
    entityManager.persist(b1);

    Building b2 = new Building("building 2");
    entityManager.persist(b2);

    Building updatedBuilding = new Building("update Building");

    Building b = repository.findById(b2.getBuilding_id()).get();
    b.setBuilding_name(updatedBuilding.getBuilding_name());
    repository.save(b);

    Building checkB = repository.findById(b.getBuilding_id()).get();

    assertThat(checkB.getBuilding_id()).isEqualTo(b2.getBuilding_id());
    assertThat(checkB.getBuilding_name()).isEqualTo(updatedBuilding.getBuilding_name());
  }

  @Test
  public void should_delete_building_by_id() {
    Building b1 = new Building("building 1");
    entityManager.persist(b1);

    Building b2 = new Building("building 2");
    entityManager.persist(b2);

    Building b3 = new Building("build=ting 3");
    entityManager.persist(b3);

    repository.deleteById(b2.getBuilding_id());

    Iterable<Building> buildings = repository.findAll();

    assertThat(buildings).hasSize(2).contains(b1, b3);
  }

  @Test
  public void should_delete_all_buildings() {
    entityManager.persist(new Building("building 1"));
    entityManager.persist(new Building("building 2"));

    repository.deleteAll();

    assertThat(repository.findAll()).isEmpty();
  }
}
