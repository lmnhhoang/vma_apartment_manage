package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.ExtraFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtraFeeRepository extends JpaRepository<ExtraFee,Long> {
    List<ExtraFee> findAllByName(String fee_type);
}
