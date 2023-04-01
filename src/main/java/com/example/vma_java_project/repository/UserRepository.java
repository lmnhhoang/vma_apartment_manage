package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.User;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String user_name);

  Boolean existsByUsername(String user_name);

  Boolean existsByEmail(String email);

  @Query("select u.username from User u")
  ArrayList<String> findAllUsername();

  @Query("select u.email from User u")
  ArrayList<String> findAllEmail();
}
