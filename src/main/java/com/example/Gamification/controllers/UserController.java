package com.example.Gamification.controllers;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gamification.entities.InsuranceApplication;
import com.example.Gamification.entities.LoanApplication;
import com.example.Gamification.entities.Transactions;
import com.example.Gamification.entities.Users;
import com.example.Gamification.repositories.UserRepository;
import com.example.Gamification.services.TransactionService;
import com.example.Gamification.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public String addUser(@RequestBody Users user) {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder(10);
        // Ensure the first digit is not zero
        accountNumber.append(random.nextInt(9) + 1); 
        // Generate the remaining digits
        for (int i = 1; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }
        

        user.setAccoutnNo(accountNumber.toString());
        userService.add(user);
        return "User added successfully";
    }

    @PostMapping("/transactions")
    public String addTransaction(@RequestBody Map<String,String> requestbody) {
        Long userId = Long.parseLong(requestbody.get("userId"));
        double amount=Double.parseDouble(requestbody.get("amount"));
        String receiverNo=requestbody.get("accountNo");
        
        return transactionService.processTransaction(userId, amount,receiverNo);
    }

    @PostMapping("/getAllTransactions/{userId}")
    public List<Transactions> applyForInsurance(@PathVariable Long userId) {
        
        return userService.readAllTransactions(userId);
    }

    @PostMapping("/getLoans/{userId}")
    public List<LoanApplication> readLoans(@PathVariable Long userId) {
        return userService.readLoans(userId);
    }

    @PostMapping("/getInsurances/{userId}")
    public List<InsuranceApplication> readInsurances(@PathVariable Long userId) {
        return userService.readInsurances(userId);
    }

    @PostMapping("/myProfile/{userId}")
    public Users getProfile(@PathVariable Long userId) {
        return userRepository.findById(userId).get();
    }

    
}