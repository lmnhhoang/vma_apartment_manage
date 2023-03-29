package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.Dwellers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DwellerRepository extends JpaRepository<Dwellers, Long> {

  @Query("select count(d.dweller_id) from Dwellers d where d.apartment_id = ?1")
  Double countDwellersByApartment_id(Long apartmentId);

  @Query("select d from Dwellers d where d.apartment_id = ?1")
  List<Dwellers> findDwellersByApartment_id(@Param("apartment_id") Long apartmentId);
}
