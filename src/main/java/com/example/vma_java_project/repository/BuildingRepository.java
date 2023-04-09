package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.Building;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

  @Query("select b from Building b where b.building_name like %:name%")
  List<Building> findByBuilding_nameContaining(@Param("name") String name);


}
