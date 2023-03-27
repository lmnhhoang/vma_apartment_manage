package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.ExtraFee;
import com.example.vma_java_project.repository.ExtraFeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ExtraFeeController {

  @Autowired
  ExtraFeeRepository extraFeeRepository;

  //Get all extraFee
  @GetMapping("/ex_fee")
  public List<ExtraFee> getAllExFee() {
    return extraFeeRepository.findAll();
  }

  //Create fee rest api
  @PostMapping("/ex_fee")
  public ExtraFee createExFee(@RequestBody ExtraFee extraFee) {
    return extraFeeRepository.save(extraFee);
  }

  // get fee by id rest api
  @GetMapping("/ex_fee/{id}")
  public ResponseEntity<ExtraFee> getExFeeById(@PathVariable Long id) {
    ExtraFee extraFee = extraFeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Extra Fee not exist with id :" + id));
    return ResponseEntity.ok(extraFee);
  }

  //get fee by fee name
  @GetMapping("/ex_fee/{fee_type}")
  public ResponseEntity<List<ExtraFee>> getExFeeByName(@PathVariable String fee_type) {
    List<ExtraFee> extraFee = null;
    try {
      extraFee = extraFeeRepository.findAllByName(fee_type);
    } catch (ResourceNotFoundException e) {
    }
    return ResponseEntity.ok(extraFee);
  }

  // update fee rest api
  @PutMapping("/ex_fee/{id}")
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
  @DeleteMapping("/ex_fee/{id}")
  public ResponseEntity<Map<String, Boolean>> deleteExFee(@PathVariable Long id) {
    ExtraFee extraFee = extraFeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Extra Fee not exist with id :" + id));

    extraFeeRepository.delete(extraFee);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }
}
