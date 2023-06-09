package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.Apartment;
import com.example.vma_java_project.repository.ApartmentRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
@Tag(name = "Apartment", description = "Apartment management APIs")
public class ApartmentController {

  @Autowired
  ApartmentRepository apartmentRepository;

  //Get all Apartment
  @GetMapping("/listAll")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<Apartment> getAllApartment() {
    return apartmentRepository.findAll();
  }

  //Create Apartment rest api
  @PostMapping("/addApartment")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public Apartment createApartment(@RequestBody Apartment apartment) {
    return apartmentRepository.save(apartment);
  }

  // get Apartment by id rest api
  @GetMapping("/listApartment/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MODERATOR')")
  public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
    Apartment apartment = apartmentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Apartment not exist with id :" + id));
    return ResponseEntity.ok(apartment);
  }

  // update Apartment rest api
  @PutMapping("/addApartment/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<Apartment> updateApartment(@PathVariable Long id,
      @RequestBody Apartment apartmentDetails) {
    Apartment apartment = apartmentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Apartment not exist with id :" + id));

    apartment.setRoomNo(apartmentDetails.getRoomNo());
    apartment.setAcreage(apartmentDetails.getAcreage());
    apartment.setNumOfRoom(apartmentDetails.getNumOfRoom());
    apartment.setStatus(apartmentDetails.getStatus());
    apartment.setDescription(apartmentDetails.getDescription());
    apartment.setBuilding_id(apartmentDetails.getBuilding_id());

    Apartment updatedApartment = apartmentRepository.save(apartment);
    return ResponseEntity.ok(updatedApartment);
  }

  // delete Apartment rest api
  @DeleteMapping("/deleteApartment/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<Map<String, Boolean>> deleteApartment(@PathVariable Long id) {
    Apartment apartment = apartmentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Apartment not exist with id :" + id));

    apartmentRepository.delete(apartment);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }

  //get apartment in building
  @GetMapping("/listApartment")
  @ResponseBody
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MODERATOR')")
  public List<Apartment> getApartmentInBuilding(@RequestParam String building) {
    return apartmentRepository.findApartmentByBuildingIn(Long.parseLong(building));
  }

  //count apartment in building
  @GetMapping("/countApartment")
  @ResponseBody
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MODERATOR')")
  public Double countApartmentInBuilding(@RequestParam String building) {
    return apartmentRepository.countApartmentByInBuilding(Long.parseLong(building));
  }

  @GetMapping("/searchApartment")
  @ResponseBody
  public List<Apartment> searchApartmentByName(@RequestParam("search") String search,
      @RequestParam("type") String type) {
    switch (type) {
      case "email":
        return apartmentRepository.searchApartmentByEmail(search);
      case "acreage":
        return apartmentRepository.searchApartmentByAcreage(search);
      default:
        return apartmentRepository.searchApartmentByRoomNo(search);
    }
  }

  @GetMapping("/getApartment")
  @ResponseBody
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MODERATOR')")
  public ResponseEntity<Map<String, Object>> getAllApartment(
      @RequestParam(required = false) String building, @RequestParam(required = false) String room,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "5") Integer size,
      @RequestParam(defaultValue = "apartment_id") String sortBy) {
    try {
      List<Apartment> apartments = new ArrayList<Apartment>();
      Pageable paging = PageRequest.of(page, size);

      Page<Apartment> pageTuts;
      if (building != null) {
        if (room != null) {
          pageTuts = apartmentRepository.findApartmentInBuildingAndRoom(room,
              Long.parseLong(building), paging);
        } else {
          pageTuts = apartmentRepository.findApartmentInBuilding(Long.parseLong(building), paging);
        }
      } else if (building == null && room != null) {
        pageTuts = apartmentRepository.searchApartmentByRoom(room, paging);
      } else {
        pageTuts = apartmentRepository.findAll(paging);
      }

      apartments = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();
      response.put("apartments", apartments);
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
