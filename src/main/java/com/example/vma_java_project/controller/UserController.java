package com.example.vma_java_project.controller;

import com.example.vma_java_project.exception.ResourceNotFoundException;
import com.example.vma_java_project.model.User;
import com.example.vma_java_project.repository.UserRepository;
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
@Tag(name = "User", description = "User management APIs")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  //Get all User
  @GetMapping("/user")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  //Create user rest api
  @PostMapping("/addUser")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public User createUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  // get user by id rest api
  @GetMapping("/user/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User employee = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
    return ResponseEntity.ok(employee);
  }

  // update user rest api
  @PutMapping("/addUser/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

    user.setUsername(userDetails.getUsername());
    user.setEmail(userDetails.getEmail());
    user.setPassword(userDetails.getPassword());

    User updatedUser = userRepository.save(user);
    return ResponseEntity.ok(updatedUser);
  }

  // delete user rest api
  @DeleteMapping("/deleteUser/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

    userRepository.delete(user);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }
}
