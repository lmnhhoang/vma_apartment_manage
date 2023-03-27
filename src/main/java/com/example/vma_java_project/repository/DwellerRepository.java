package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.Dwellers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DwellerRepository extends JpaRepository<Dwellers,Long> {
}
