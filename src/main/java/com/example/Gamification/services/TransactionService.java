package com.example.Gamification.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Gamification.entities.Rewards;
import com.example.Gamification.entities.Transactions;
import com.example.Gamification.entities.Users;
import com.example.Gamification.repositories.RewardRepository;
import com.example.Gamification.repositories.TransactionRepository;
import com.example.Gamification.repositories.UserRepository;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RewardRepository rewardRepository;

    public String processTransaction(Long userId, double amount,String receiverNo) {
        Users user = userRepository.findById(userId).orElseThrow();
        Users receiver=userRepository.findByAccoutnNo(receiverNo);

        if(user.getBalance()-amount<1000){
            return "Insufficient balance, Maintain minimum balance to make transactions.";
        }

        int score = calculateScore(userId,amount);
        user.setScore(user.getScore() + score);
        List<Rewards> rewards = checkAndAwardRewards(user);
        user.setBalance(user.getBalance()-amount);
        user.setRewards(rewards);
        userRepository.save(user);

        receiver.setBalance(receiver.getBalance()+amount);
        userRepository.save(receiver);
        

        Transactions transaction = new Transactions();
        transaction.setAmount(amount);
        transaction.setDate(LocalDate.now());
        transaction.setUser(user);
        transaction.setUserId(user.getId());
        transactionRepository.save(transaction);

        updateUserRankings();
        
        return "Transaction processed and score updated.";

    }

    private int calculateScore(Long userId,double amount) {
        Users user = userRepository.findById(userId).orElseThrow();
        int transactionCount=0,score=0;
        List<Transactions> transactions = transactionRepository.findAllByUserId(userId);
        for(Transactions transaction:transactions){
            if(transaction.getDate()==LocalDate.now()){
                transactionCount++;
            }
        }
        if(transactionCount>=10){
            score=100;
            user.setCreditScore(5);
            userRepository.save(user);
        }


        if(amount>=5000.0&&amount<=10000.0){
            return score+5;
        }else if(amount>10000.0&&amount<=25000.0){
            return score+10;
        }else if(amount>25000.0){
            return score+20;
        }  
        return score;
    }

    private List<Rewards> checkAndAwardRewards(Users user) {
        List<Rewards> rewards = rewardRepository.findAll();
        List<Rewards> awarded=new ArrayList<>();
        for (Rewards reward : rewards) {
            if (user.getScore() >= reward.getScoreThreshold()) {
                awarded.add(reward);
            }
        }
        return rewards;
    }

    private void updateUserRankings() {
        List<Users> allUsers = userRepository.findAll();

        // Sort users by credit score in descending order
        allUsers.sort((u1, u2) -> Double.compare(u2.getCreditScore(), u1.getCreditScore()));

        // Assign rankings
        for (int i = 0; i < allUsers.size(); i++) {
            Users user = allUsers.get(i);
            user.setRanking(i + 1); 
            userRepository.save(user); 
        }
    }
    
}
