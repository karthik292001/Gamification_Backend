package com.example.Gamification.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int score;
    private int creditScore;
    private String accoutnNo;
    private double balance;
    private int ranking;
    
    @OneToMany
    @JoinColumn(name = "user") 
    private List<Rewards> rewards = new ArrayList<>();
    
    //loan
    //score
    private double monthlyIncome;
    private double existingDebt;
    

    //insurance
    private int age;
    private String healthStatus;



   // Total Existing Debt=Personal Loan Balance+Mortgage Balance+Credit Card Balances+Auto Loan Balance+Student Loan Balance+Other Liabilities
}
