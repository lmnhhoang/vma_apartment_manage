package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.Dwellers;
import com.example.vma_java_project.model.ExtraFee;
import com.example.vma_java_project.repository.DwellerRepository;
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
public class DwellerController {
    @Autowired
    DwellerRepository dwellerRepository;

    //Get all dweller
    @GetMapping("/listCivil")
    public List<Dwellers> getAllDweller(){
        return dwellerRepository.findAll();
    }

    //Create dweller rest api
    @PostMapping("/addCivil")
    public Dwellers createDweller(@RequestBody Dwellers dweller){
        return dwellerRepository.save(dweller);
    }

    // get dweller by id rest api
    @GetMapping("/listCivil/{id}")
    public ResponseEntity<Dwellers> getDwellerById(@PathVariable Long id) {
        Dwellers dweller = dwellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dweller not exist with id :" + id));
        return ResponseEntity.ok(dweller);
    }

    // update dweller rest api
    @PutMapping("/civil/{id}")
    public ResponseEntity<Dwellers> updateDweller(@PathVariable Long id, @RequestBody Dwellers dwellerDetails){
        Dwellers dweller = dwellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dweller not exist with id :" + id));

        dweller.setCid(dwellerDetails.getCid());
        dweller.setFullname(dwellerDetails.getFullname());
        dweller.setEmail(dwellerDetails.getEmail());
        dweller.setPhone(dwellerDetails.getPhone());
        dweller.setBirthday(dwellerDetails.getBirthday());
        dweller.setGender(dwellerDetails.getGender());
        dweller.setDweller_aprtm(dwellerDetails.getDweller_aprtm());

        Dwellers updatedDweller = dwellerRepository.save(dweller);
        return ResponseEntity.ok(updatedDweller);
    }

    // delete dweller rest api
    @DeleteMapping("/civil/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDweller(@PathVariable Long id){
        Dwellers dweller = dwellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dweller not exist with id :" + id));

        dwellerRepository.delete(dweller);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
