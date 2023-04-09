package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.Apartment;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

  @Query("select count (a.apartment_id) from Apartment a where a.building_id = :building_id")
  Double countApartmentByInBuilding(@Param("building_id") long buildingId);

  @Query("select a from Apartment a where a.building_id = :building_id")
  List<Apartment> findApartmentByBuildingIn(@Param("building_id") long buildingId);

  @Query("select a from Apartment a where a.roomNo like %:search%")
  List<Apartment> searchApartmentByRoomNo(@Param("search") String search);

  @Query("select a from Apartment a where a.presenter_email like %:search%")
  List<Apartment> searchApartmentByEmail(@Param("search") String search);

  @Query("select a from Apartment a where cast(a.acreage as string) like %:search%")
  List<Apartment> searchApartmentByAcreage(@Param("search") String search);

  @Query("select a from Apartment a where a.building_id = ?1")
  Page<Apartment> findApartmentInBuilding(@Param("building_id") long buildingId, Pageable pageable);

  @Query("select a from Apartment a where a.roomNo like %:search%")
  Page<Apartment> searchApartmentByRoom(@Param("search") String search, Pageable pageable);

  @Query("select a from Apartment a where a.roomNo like %:search% and a.building_id = :building_id")
  Page<Apartment> findApartmentInBuildingAndRoom(@Param("search") String search,
      @Param("building_id") long buildingId, Pageable pageable);

  @Query("select a.apartment_id from Apartment a")
  List<Long> getAllId();

  @Query("select a.apartment_id,a.roomNo from Apartment a")
  List<Apartment> getBaseApartment();
}
