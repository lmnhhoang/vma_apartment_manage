package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.Building;
import com.example.vma_java_project.repository.BuildingRepository;
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
@Tag(name = "Building", description = "Building management APIs")
public class BuildingController {

  @Autowired
  BuildingRepository buildingRepository;

  //Get all User
  @GetMapping("/building")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MODERATOR')")
  public List<Building> getAllBuilding() {
    return buildingRepository.findAll();
  }

  //Create user rest api
  @PostMapping("/addBuilding")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public Building createBuilding(@RequestBody Building building) {
    return buildingRepository.save(building);
  }

  // get user by id rest api
  @GetMapping("/building/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MODERATOR')")
  public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
    Building building = buildingRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Building not exist with id :" + id));
    return ResponseEntity.ok(building);
  }

  // update user rest api
  @PutMapping("/addBuilding/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<Building> updateBuilding(@PathVariable Long id,
      @RequestBody Building buildingDetails) {
    Building building = buildingRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Building not exist with id :" + id));

    building.setBuilding_name(buildingDetails.getBuilding_name());

    Building updatedBuilding = buildingRepository.save(building);
    return ResponseEntity.ok(updatedBuilding);
  }

  // delete user rest api
  @DeleteMapping("/deleteBuilding/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<Map<String, Boolean>> deleteBuilding(@PathVariable Long id) {
    Building building = buildingRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Building not exist with id :" + id));

    buildingRepository.delete(building);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }
}
