package com.example.Gamification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gamification.entities.InsuranceApplication;

public interface InsuranceApplicationRepository extends JpaRepository<InsuranceApplication, Long> {

    List<InsuranceApplication> findAllByUserId(Long userId);

    List<InsuranceApplication> findAllByStatus(String string);

}
