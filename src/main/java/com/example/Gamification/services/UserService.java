package com.example.Gamification.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Gamification.entities.InsuranceApplication;
import com.example.Gamification.entities.LoanApplication;
import com.example.Gamification.entities.Transactions;
import com.example.Gamification.entities.Users;
import com.example.Gamification.repositories.InsuranceApplicationRepository;
import com.example.Gamification.repositories.LoanApplicationRepository;
import com.example.Gamification.repositories.TransactionRepository;
import com.example.Gamification.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InsuranceApplicationRepository insuranceApplicationRepository;

    @Autowired
    LoanApplicationRepository loanApplicationRepository;

    public List<Transactions> readAllTransactions(Long userId) {
        return transactionRepository.findAllByUserId(userId);
    }
    public void add(Users user) {
        userRepository.save(user);
    }
    public List<LoanApplication> readLoans(Long userId) {
        return loanApplicationRepository.findAllByUserId(userId);
       
    }
    public List<InsuranceApplication> readInsurances(Long userId) {
        return insuranceApplicationRepository.findAllByUserId(userId);
    }
    
}
