package com.example.vma_java_project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.vma_java_project.model.User;
import com.example.vma_java_project.repository.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepoTest {

  @Autowired
  UserRepository repository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void should_find_no_user_if_repository_is_empty() {
    Iterable<User> users = repository.findAll();

    assertThat(users).isEmpty();
  }

  @Test
  public void should_store_an_user() {
    User user = repository.saveAndFlush(new User("user1", "user1@gmail.com", "user1"));

    assertThat(user).hasFieldOrPropertyWithValue("username", "user1");
    assertThat(user).hasFieldOrPropertyWithValue("email", "user1@gmail.com");
    assertThat(user).hasFieldOrPropertyWithValue("password", "user1");
  }

  @Test
  public void should_find_all_users() {
    User u1 = new User("user1", "user1@gmail.com", "user1");
    entityManager.persist(u1);

    User u2 = new User("user2", "user2@gmail.com", "user2");
    entityManager.persist(u2);

    User u3 = new User("user3", "user3@gmail.com", "user3");
    entityManager.persist(u3);

    Iterable<User> users = repository.findAll();

    assertThat(users).hasSize(3).contains(u1, u2, u3);
  }

  @Test
  public void should_find_user_by_id() {
    User u1 = new User("user1", "user1@gmail.com", "user1");
    entityManager.persist(u1);

    User u2 = new User("user2", "user2@gmail.com", "user2");
    entityManager.persist(u2);

    User foundUser = repository.findById(u2.getUser_id()).get();

    assertThat(foundUser).isEqualTo(u2);
  }

  @Test
  public void should_find_username_user() {
    User u1 = new User("user1", "user1@gmail.com", "user1");
    entityManager.persist(u1);

    User u2 = new User("user2", "user2@gmail.com", "user2");
    entityManager.persist(u2);

    User u3 = new User("user3", "user3@gmail.com", "user3");
    entityManager.persist(u3);

    Optional<User> users = repository.findByUsername("user1");

    assertThat(users).contains(u1);
  }

  @Test
  public void should_exist_username_user() {
    User u1 = new User("user1", "user1@gmail.com", "user1");
    entityManager.persist(u1);

    User u2 = new User("user2", "user2@gmail.com", "user2");
    entityManager.persist(u2);

    User u3 = new User("user3", "user3@gmail.com", "user3");
    entityManager.persist(u3);

    Boolean existsed = repository.existsByUsername("user1");

    assertThat(existsed).isEqualTo(Boolean.TRUE);
  }

  @Test
  public void should_exist_email_user() {
    User u1 = new User("user1", "user1@gmail.com", "user1");
    entityManager.persist(u1);

    User u2 = new User("user2", "user2@gmail.com", "user2");
    entityManager.persist(u2);

    User u3 = new User("user3", "user3@gmail.com", "user3");
    entityManager.persist(u3);

    Boolean existsed = repository.existsByEmail("user2@gmail.com");

    assertThat(existsed).isEqualTo(Boolean.TRUE);
  }

  @Test
  public void should_find_all_username() {
    User u1 = new User("user1", "user1@gmail.com", "user1");
    entityManager.persist(u1);

    User u2 = new User("user2", "user2@gmail.com", "user2");
    entityManager.persist(u2);

    User u3 = new User("user3", "user3@gmail.com", "user3");
    entityManager.persist(u3);

    ArrayList<String> users = repository.findAllUsername();

    assertThat(users).hasSize(3).contains(u1.getUsername(), u2.getUsername(), u3.getUsername());
  }

  @Test
  public void should_find_all_email() {
    User u1 = new User("user1", "user1@gmail.com", "user1");
    entityManager.persist(u1);

    User u2 = new User("user2", "user2@gmail.com", "user2");
    entityManager.persist(u2);

    User u3 = new User("user3", "user3@gmail.com", "user3");
    entityManager.persist(u3);

    ArrayList<String> users = repository.findAllEmail();

    assertThat(users).hasSize(3).contains(u1.getEmail(), u2.getEmail(), u3.getEmail());
  }

  @Test
  public void should_update_user_by_id() {
    User u1 = new User("user1", "user1@gmail.com", "user1");
    entityManager.persist(u1);

    User u2 = new User("user2", "user2@gmail.com", "user2");
    entityManager.persist(u2);

    User updatedUser = new User("user1_update", "user1_update@gmail.com", "user1_update");

    User u = repository.findById(u2.getUser_id()).get();
    u.setUsername(updatedUser.getUsername());
    u.setEmail(updatedUser.getEmail());
    u.setPassword(updatedUser.getPassword());
    repository.saveAndFlush(u);

    User checkU = repository.findById(u.getUser_id()).get();

    assertThat(checkU.getUser_id()).isEqualTo(u2.getUser_id());
    assertThat(checkU.getUsername()).isEqualTo(updatedUser.getUsername());
    assertThat(checkU.getEmail()).isEqualTo(updatedUser.getEmail());
    assertThat(checkU.getPassword()).isEqualTo(updatedUser.getPassword());
  }

  @Test
  public void should_delete_user_by_id() {
    User u1 = new User("user1", "user1@gmail.com", "user1");
    entityManager.persist(u1);

    User u2 = new User("user2", "user2@gmail.com", "user2");
    entityManager.persist(u2);

    User u3 = new User("user3", "user3@gmail.com", "user3");
    entityManager.persist(u3);

    repository.deleteById(u2.getUser_id());

    Iterable<User> users = repository.findAll();

    assertThat(users).hasSize(2).contains(u1, u3);
  }

  @Test
  public void should_delete_all_tusers() {
    entityManager.persist(new User("user1", "user1@gmail.com", "user1"));
    entityManager.persist(new User("user2", "user2@gmail.com", "user2"));

    repository.deleteAll();

    assertThat(repository.findAll()).isEmpty();
  }
}
