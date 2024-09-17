package com.example.Gamification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gamification.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByAccoutnNo(String receiverNo);

}
