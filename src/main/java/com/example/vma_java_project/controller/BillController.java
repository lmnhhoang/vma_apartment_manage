package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.Bill;
import com.example.vma_java_project.repository.BillRepository;
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
public class BillController {

  @Autowired
  BillRepository billRepository;

  //Get all Bill
  @GetMapping("/listAllBill")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<Bill> getAllBill() {
    return billRepository.findAll();
  }

  //Create Bill rest api
  @PostMapping("/addBill")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Bill createBill(@RequestBody Bill bill) {
    return billRepository.save(bill);
  }

  // get Bill by id rest api
  @GetMapping("/listBill/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
  public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
    Bill bill = billRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Bill not exist with id :" + id));
    return ResponseEntity.ok(bill);
  }

  // update bill rest api
  @PutMapping("/addBill/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill billrDetails) {
    Bill bill = billRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Bill not exist with id :" + id));

    bill.setApartment_id(billrDetails.getApartment_id());
    bill.setCreatedDate(billrDetails.getCreatedDate());
    bill.setE_fee(billrDetails.getE_fee());
    bill.setW_fee(billrDetails.getW_fee());
    bill.setEx_fee(billrDetails.getEx_fee());
    bill.setStatus(billrDetails.getStatus());
    bill.setPaid_day(billrDetails.getPaid_day());

    Bill updatedBill = billRepository.save(bill);
    return ResponseEntity.ok(updatedBill);
  }

  // delete bill rest api
  @DeleteMapping("/deleteBill/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Map<String, Boolean>> deleteBill(@PathVariable Long id) {
    Bill bill = billRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Dweller not exist with id :" + id));

    billRepository.delete(bill);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }
}
