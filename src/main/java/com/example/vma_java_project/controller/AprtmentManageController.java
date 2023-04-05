package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.ApartmentManage;
import com.example.vma_java_project.repository.AprtManageRepository;
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
public class AprtmentManageController {

  @Autowired
  AprtManageRepository aprtManageRepository;

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
  public ApartmentManage ScheduleAddManage() {
    ApartmentManage apartmentManage = new ApartmentManage();
    return aprtManageRepository.save(apartmentManage);
  }
}
