package com.example.Gamification.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gamification.entities.Insurance;
import com.example.Gamification.entities.InsuranceApplication;
import com.example.Gamification.entities.LoanApplication;
import com.example.Gamification.entities.Loans;
import com.example.Gamification.entities.Rewards;
import com.example.Gamification.services.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/rewards/add")
    public String add(@RequestBody Rewards reward) {
        applicationService.addReward(reward);
        return "Reward added successfully";
    }

    @PostMapping("/loan/add")
    public String add(@RequestBody Loans loan) {
        applicationService.addLoan(loan);
        return "Loan added successfully";
    }

    @PostMapping("/insurance/add")
    public String add(@RequestBody Insurance insurance) {
        applicationService.addInsurance(insurance);
        return "Insurance added successfully";
    }


    @GetMapping("/getAllLoans")
    public List<Loans> readLoans() {
        return applicationService.readLoans();
    }
   
    @GetMapping("/getInsurances")
    public List<Insurance> readInsurances() {
        return applicationService.readInsurances();
    }


    @PostMapping("/loanApplication")
    public String applyForLoan(@RequestBody LoanApplication loanApplication) {
        return applicationService.applyForLoan(loanApplication);
    }

    @PostMapping("/insuranceApplication")
    public String applyForInsurance(@RequestBody InsuranceApplication insuranceApplication) {
        return applicationService.applyForInsurance(insuranceApplication);
    }
    
    @PostMapping("/updateLoan/{id}")
    public String updateLoan(@PathVariable Long id){
        return applicationService.updateLoan(id);
    }

    @PostMapping("/updateInsurance/{id}")
    public String updateInsurance(@PathVariable Long id){
        return applicationService.updateInsurance(id);
    }

    @GetMapping("/laonApplications")
    public List<LoanApplication> laonApplications(){
        return applicationService.laonApplications();
    }

    @GetMapping("/insuranceApplications")
    public List<InsuranceApplication> insuranceApplications(){
        return applicationService.laonAppinsuranceApplicationslications();
    }

}
