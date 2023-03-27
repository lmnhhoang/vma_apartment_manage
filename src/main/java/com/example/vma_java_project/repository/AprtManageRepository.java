package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.ApartmentManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AprtManageRepository extends JpaRepository<ApartmentManage,Long> {
}
