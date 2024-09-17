package com.example.Gamification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gamification.entities.Rewards;

public interface RewardRepository extends JpaRepository<Rewards, Long> {

}
