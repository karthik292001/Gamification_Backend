package com.example.Gamification.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class InsuranceApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private double coverageAmount;
    private String provider;
    private String type;
    private int years;
    private String status="pending";
    @ManyToOne
    @JoinColumn(name = "user")
    private Users user; 
    // other insurance application fields
}
