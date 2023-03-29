package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.ExtraFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraFeeRepository extends JpaRepository<ExtraFee, Long> {

  @Query("select sum(e.fee_value) from ExtraFee e")
  Double calculateExtraFee();
}
