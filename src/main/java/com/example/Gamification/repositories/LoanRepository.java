package com.example.Gamification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gamification.entities.Loans;

public interface LoanRepository extends JpaRepository<Loans, Long> {

}
