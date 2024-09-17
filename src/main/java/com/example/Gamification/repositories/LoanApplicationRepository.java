package com.example.Gamification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gamification.entities.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findAllByUserId(Long userId);

    List<LoanApplication> findAllByStatus(String string);

}
