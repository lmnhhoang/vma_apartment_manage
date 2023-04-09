package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.ApartmentManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AprtManageRepository extends JpaRepository<ApartmentManage, Long> {

  @Query("select am.e_value from ApartmentManage am where MONTH(am.record_date) = ?1 and am.apartment_id = ?2")
  Long getEValueByMonth(int month, long apartment_id);

  @Query("select am.w_value from ApartmentManage am where MONTH(am.record_date) = ?1 and am.apartment_id = ?2")
  Long getWValueByMonth(int month, long apartment_id);
}
