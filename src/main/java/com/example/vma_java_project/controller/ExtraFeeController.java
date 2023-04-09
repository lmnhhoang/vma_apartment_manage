package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.ExtraFee;
import com.example.vma_java_project.repository.ExtraFeeRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "Extra Fee", description = "Extra fee management APIs")
public class ExtraFeeController {

  @Autowired
  ExtraFeeRepository extraFeeRepository;

  //Get all extraFee
  @GetMapping("/fee")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public List<ExtraFee> getAllExFee() {
    return extraFeeRepository.findAll();
  }

  //Create fee rest api
  @PostMapping("/addFee")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ExtraFee createExFee(@RequestBody ExtraFee extraFee) {
    return extraFeeRepository.save(extraFee);
  }

  // get fee by id rest api
  @GetMapping("/fee/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<ExtraFee> getExFeeById(@PathVariable Long id) {
    ExtraFee extraFee = extraFeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Extra Fee not exist with id :" + id));
    return ResponseEntity.ok(extraFee);
  }


  // update fee rest api
  @PutMapping("/addFee/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<ExtraFee> updateExFee(@PathVariable Long id,
      @RequestBody ExtraFee exFeeDetails) {
    ExtraFee extraFee = extraFeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Extra Fee not exist with id :" + id));

    extraFee.setFee_type(exFeeDetails.getFee_type());
    extraFee.setFee_value(exFeeDetails.getFee_value());

    ExtraFee updatedExFee = extraFeeRepository.save(extraFee);
    return ResponseEntity.ok(updatedExFee);
  }

  // delete fee rest api
  @DeleteMapping("/deleteFee/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<Map<String, Boolean>> deleteExFee(@PathVariable Long id) {
    ExtraFee extraFee = extraFeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Extra Fee not exist with id :" + id));

    extraFeeRepository.delete(extraFee);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }

  //Calculate extra fee
  @GetMapping("/fee/total")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public Double getExFeeTotal() {
    return extraFeeRepository.calculateExtraFee();
  }
}
