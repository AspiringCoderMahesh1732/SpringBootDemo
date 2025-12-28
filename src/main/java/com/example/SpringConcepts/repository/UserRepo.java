package com.example.SpringConcepts.repository;

import com.example.SpringConcepts.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, String> {
    public Users findByUsername(String username);
}

