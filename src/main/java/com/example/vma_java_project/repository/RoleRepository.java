package com.example.vma_java_project.repository;

import com.example.vma_java_project.model.ERole;
import com.example.vma_java_project.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(ERole name);
}
