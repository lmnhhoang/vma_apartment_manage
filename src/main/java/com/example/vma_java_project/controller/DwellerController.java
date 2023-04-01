package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.Dwellers;
import com.example.vma_java_project.repository.DwellerRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DwellerController {

  @Autowired
  DwellerRepository dwellerRepository;

  //Get all dweller
  @GetMapping("/listAllCivil")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public List<Dwellers> getAllDweller() {
    return dwellerRepository.findAll();
  }

  //Create dweller rest api
  @PostMapping("/addCivil")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public Dwellers createDweller(@RequestBody Dwellers dweller) {
    return dwellerRepository.save(dweller);
  }

  // get dweller by id rest api
  @GetMapping("/listCivil/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<Dwellers> getDwellerById(@PathVariable Long id) {
    Dwellers dweller = dwellerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Dweller not exist with id :" + id));
    return ResponseEntity.ok(dweller);
  }

  // update dweller rest api
  @PutMapping("/addCivil/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<Dwellers> updateDweller(@PathVariable Long id,
      @RequestBody Dwellers dwellerDetails) {
    Dwellers dweller = dwellerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Dweller not exist with id :" + id));

    dweller.setCid(dwellerDetails.getCid());
    dweller.setFullname(dwellerDetails.getFullname());
    dweller.setEmail(dwellerDetails.getEmail());
    dweller.setPhone(dwellerDetails.getPhone());
    dweller.setBirthday(dwellerDetails.getBirthday());
    dweller.setGender(dwellerDetails.getGender());
    dweller.setApartment_id(dwellerDetails.getApartment_id());

    Dwellers updatedDweller = dwellerRepository.save(dweller);
    return ResponseEntity.ok(updatedDweller);
  }

  // delete dweller rest api
  @DeleteMapping("/deleteCivil/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<Map<String, Boolean>> deleteDweller(@PathVariable Long id) {
    Dwellers dweller = dwellerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Dweller not exist with id :" + id));

    dwellerRepository.delete(dweller);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }

  //get dweller in apartment
  @GetMapping("/listCivil")
  @ResponseBody
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MODERATOR')")
  public List<Dwellers> getDwellerInApartment(@RequestParam String apartment) {
    return dwellerRepository.findDwellersByApartment_id(Long.parseLong(apartment));
  }

  //count dweller in apartment
  @GetMapping("/countCivil")
  @ResponseBody
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MODERATOR')")
  public Double countDwellerInApartment(@RequestParam String apartment) {
    return dwellerRepository.countDwellersByApartment_id(Long.parseLong(apartment));
  }
}
