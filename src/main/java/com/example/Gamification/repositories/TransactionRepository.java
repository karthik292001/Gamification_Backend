package com.example.Gamification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gamification.entities.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findAllByUserId(Long userId);

}
