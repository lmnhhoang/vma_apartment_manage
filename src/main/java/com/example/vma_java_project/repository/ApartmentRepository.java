package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.Apartment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

  @Query("select count (a.apartment_id) from Apartment a where a.building_id = ?1")
  Double countApartmentByInBuilding(@Param("building_id") long buildingId);

  @Query("select a from Apartment a where a.building_id = ?1")
  List<Apartment> findApartmentByBuildingIn(@Param("building_id") long buildingId);
}
