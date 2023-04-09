package com.example.vma_java_project;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.vma_java_project.model.ExtraFee;
import com.example.vma_java_project.model.User;
import com.example.vma_java_project.repository.ExtraFeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ExtraFeeRepoTest {
  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  ExtraFeeRepository repository;

  @Test
  public void should_find_no_fee_if_repository_is_empty() {
    Iterable<ExtraFee> fees = repository.findAll();

    assertThat(fees).isEmpty();
  }
  @Test
  public void should_store_fee() {
    ExtraFee fee = repository.save(new ExtraFee("f1",10L));

    assertThat(fee).hasFieldOrPropertyWithValue("fee_type", "f1");
    assertThat(fee).hasFieldOrPropertyWithValue("value", 10L);
  }
}
