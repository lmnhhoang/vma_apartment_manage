package com.example.vma_java_project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.vma_java_project.model.Building;
import com.example.vma_java_project.model.ExtraFee;
import com.example.vma_java_project.repository.ExtraFeeRepository;
import java.util.List;
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
    ExtraFee fee = repository.save(new ExtraFee("F1",10L));

    assertThat(fee).hasFieldOrPropertyWithValue("fee_type", "F1");
    assertThat(fee).hasFieldOrPropertyWithValue("fee_value", 10L);
  }
  @Test
  public void should_find_all_fees() {
    ExtraFee f1 = new ExtraFee("F1",10L);
    entityManager.persist(f1);

    ExtraFee f2 = new ExtraFee("F2",11L);
    entityManager.persist(f2);

    ExtraFee f3 = new ExtraFee("F3",12L);
    entityManager.persist(f3);

    ExtraFee f4 = new ExtraFee("F4",13L);
    entityManager.persist(f4);

    Iterable<ExtraFee> fees = repository.findAll();

    assertThat(fees).hasSize(4).contains(f1,f2,f3,f4);
  }
  @Test
  public void should_find_fee_by_id() {
    ExtraFee f1 = new ExtraFee("F1",10L);
    entityManager.persist(f1);

    ExtraFee f2 = new ExtraFee("F2",11L);
    entityManager.persist(f2);

    ExtraFee f3 = new ExtraFee("F3",12L);
    entityManager.persist(f3);

    ExtraFee f4 = new ExtraFee("F4",13L);
    entityManager.persist(f4);

    ExtraFee foundFee = repository.findById(f2.getFee_id()).get();

    assertThat(foundFee).isEqualTo(f2);
  }
  @Test
  public void should_update_fee_by_id() {
    ExtraFee f1 = new ExtraFee("F1",10L);
    entityManager.persist(f1);

    ExtraFee f2 = new ExtraFee("F2",11L);
    entityManager.persist(f2);

    ExtraFee f3 = new ExtraFee("F3",12L);
    entityManager.persist(f3);

    ExtraFee f4 = new ExtraFee("F4",13L);
    entityManager.persist(f4);

    ExtraFee updatedFee = new ExtraFee("FU2",13L);

    ExtraFee f = repository.findById(f2.getFee_id()).get();
    f.setFee_type(updatedFee.getFee_type());
    f.setFee_value(updatedFee.getFee_value());
    repository.save(f);

    ExtraFee checkF = repository.findById(f.getFee_id()).get();

    assertThat(checkF.getFee_id()).isEqualTo(f2.getFee_id());
    assertThat(checkF.getFee_type()).isEqualTo(updatedFee.getFee_type());
    assertThat(checkF.getFee_value()).isEqualTo(updatedFee.getFee_value());
  }
  @Test
  public void should_delete_fee_by_id() {
    ExtraFee f1 = new ExtraFee("F1",10L);
    entityManager.persist(f1);

    ExtraFee f2 = new ExtraFee("F2",11L);
    entityManager.persist(f2);

    ExtraFee f3 = new ExtraFee("F3",12L);
    entityManager.persist(f3);

    ExtraFee f4 = new ExtraFee("F4",13L);
    entityManager.persist(f4);

    repository.deleteById(f2.getFee_id());

    Iterable<ExtraFee> fees = repository.findAll();

    assertThat(fees).hasSize(3).contains(f1,f3,f4);
  }

  @Test
  public void should_delete_all_fees() {
    entityManager.persist(new ExtraFee("F1",10L));
    entityManager.persist(new ExtraFee("F2",11L));

    repository.deleteAll();

    assertThat(repository.findAll()).isEmpty();
  }
  @Test
  public void should_get_total_fee() {
    ExtraFee f1 = new ExtraFee("F1",10L);
    entityManager.persist(f1);

    ExtraFee f2 = new ExtraFee("F2",11L);
    entityManager.persist(f2);

    ExtraFee f3 = new ExtraFee("F3",12L);
    entityManager.persist(f3);

    ExtraFee f4 = new ExtraFee("F4",13L);
    entityManager.persist(f4);

    Double totalFee = repository.calculateExtraFee();
    Long i = 46L;
    String s = i + "";
    assertThat(totalFee).isEqualTo(Double.parseDouble(s));
  }
}
