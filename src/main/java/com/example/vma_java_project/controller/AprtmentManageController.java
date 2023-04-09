package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.ApartmentManage;
import com.example.vma_java_project.repository.ApartmentRepository;
import com.example.vma_java_project.repository.AprtManageRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Apartment Manage", description = "Monthly Apartment Value APIs")
public class AprtmentManageController {

  @Autowired
  AprtManageRepository aprtManageRepository;

  @Autowired
  ApartmentRepository apartmentRepository;

  //Get all apartmentManage
  @GetMapping("/listManage")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public List<ApartmentManage> getAllManage() {
    return aprtManageRepository.findAll();
  }

  //Create apartmentManage rest api
  @PostMapping("/addManage")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ApartmentManage createManage(@RequestBody ApartmentManage apartmentManage) {
    return aprtManageRepository.save(apartmentManage);
  }

  // get apartmentManage by id rest api
  @GetMapping("/listManage/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<ApartmentManage> getManageById(@PathVariable Long id) {
    ApartmentManage apartmentManage = aprtManageRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("ApartmentManage not exist with id :" + id));
    return ResponseEntity.ok(apartmentManage);
  }

  // update apartmentManage rest api
  @PutMapping("/addManage/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<ApartmentManage> updateManage(@PathVariable Long id,
      @RequestBody ApartmentManage manageDetails) {
    ApartmentManage apartmentManage = aprtManageRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("ApartmentManage not exist with id :" + id));

    apartmentManage.setE_value(manageDetails.getE_value());
    apartmentManage.setW_value(manageDetails.getW_value());
    apartmentManage.setRecord_date(manageDetails.getRecord_date());
    apartmentManage.setApartment_id(manageDetails.getApartment_id());

    ApartmentManage updatedManage = aprtManageRepository.save(apartmentManage);
    return ResponseEntity.ok(updatedManage);
  }

  // delete apartmentManage rest api
  @DeleteMapping("/deleteManage/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<Map<String, Boolean>> deleteManage(@PathVariable Long id) {
    ApartmentManage apartmentManage = aprtManageRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("ApartmentManage not exist with id :" + id));

    aprtManageRepository.delete(apartmentManage);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }

  @Scheduled(cron = "0 0 5 10 * ?")
  public void ScheduleAddManage() {
    LocalDate currentdate = LocalDate.now();
    int currentMonth = currentdate.getMonthValue();
    List<Long> aprtIdList = apartmentRepository.getAllId();
    for (int i = 0; i < aprtIdList.size(); i++) {
      Long e_value = aprtManageRepository.getEValueByMonth(currentMonth, aprtIdList.get(i));
      Long w_value = aprtManageRepository.getWValueByMonth(currentMonth, aprtIdList.get(i));
      if (e_value == null && w_value == null) {
        e_value = 0L;
        w_value = 0L;
      }
      ApartmentManage apartmentManage = new ApartmentManage(e_value, w_value, currentdate,
          "Unchecked", aprtIdList.get(i));
      aprtManageRepository.save(apartmentManage);
    }
  }
}
