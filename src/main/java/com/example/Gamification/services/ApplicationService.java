package com.example.Gamification.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Gamification.entities.Insurance;
import com.example.Gamification.entities.InsuranceApplication;
import com.example.Gamification.entities.LoanApplication;
import com.example.Gamification.entities.Loans;
import com.example.Gamification.entities.Rewards;
import com.example.Gamification.entities.Users;
import com.example.Gamification.repositories.InsuranceApplicationRepository;
import com.example.Gamification.repositories.InsuranceRepository;
import com.example.Gamification.repositories.LoanApplicationRepository;
import com.example.Gamification.repositories.LoanRepository;
import com.example.Gamification.repositories.RewardRepository;
import com.example.Gamification.repositories.TransactionRepository;
import com.example.Gamification.repositories.UserRepository;

@Service
public class ApplicationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private InsuranceApplicationRepository insuranceApplicationRepository;

    public void addReward(Rewards reward) {
        rewardRepository.save(reward);
    }

    public void addLoan(Loans loan) {
        loanRepository.save(loan);
    }

    public void addInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
    }

    public List<Loans> readLoans() {
        return loanRepository.findAll();
    }

    public List<Insurance> readInsurances() {
        return insuranceRepository.findAll();
    }  

    public String applyForLoan(LoanApplication loanApplication) {
        Users user = userRepository.findById(loanApplication.getUserId()).orElseThrow();
        loanApplication.setUser(user);

        //if (user.getScore() >= 1000 && creditScore >= 700 && monthlyIncome >= 3000 && existingDebt <= 0.4 * monthlyIncome) {
        if(user.getCreditScore()>=10){
            loanApplicationRepository.save(loanApplication);
            user.setCreditScore(user.getCreditScore()+2);
            userRepository.save(user);
            return "Loan applied successfully";
        }

        return "You are not eligible to apply for this loan.";
    }

    public String applyForInsurance(InsuranceApplication insuranceApplication) {
        Users user = userRepository.findById(insuranceApplication.getUserId()).orElseThrow();
        insuranceApplication.setUser(user);
        // if (user.getScore() >= 800 && age >= 18 && !healthStatus.equalsIgnoreCase("High Risk")) {
        if(user.getCreditScore()>=10){
            insuranceApplicationRepository.save(insuranceApplication);
            user.setCreditScore(user.getCreditScore()+2);
            userRepository.save(user);
            return "Insurance applied successfully";
        }
        return "You are not eligible to apply for this insurance.";
    }

    public String updateLoan(Long id) {
        LoanApplication loanApplication=loanApplicationRepository.findById(id).get();
        loanApplication.setStatus("Approved");
        loanApplicationRepository.save(loanApplication);
        return "Loan updated successfully";
    }

    public String updateInsurance(Long id) {
        InsuranceApplication insuranceApplication =insuranceApplicationRepository.findById(id).get();
        insuranceApplication.setStatus("Approved");
        insuranceApplicationRepository.save(insuranceApplication);
        return "Insurance updated successfully";
    }

    public List<LoanApplication> laonApplications() {
        return loanApplicationRepository.findAllByStatus("pending");
    }

    public List<InsuranceApplication> laonAppinsuranceApplicationslications() {
        return insuranceApplicationRepository.findAllByStatus("pending");
    }

    
}

