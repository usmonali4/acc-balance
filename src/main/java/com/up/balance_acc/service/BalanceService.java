package com.up.balance_acc.service;

import org.springframework.stereotype.Service;

import com.up.balance_acc.entity.Balance;
import com.up.balance_acc.repository.BalanceRepository;

@Service
public class BalanceService {
    private final BalanceRepository balanceRepo;

    public BalanceService(BalanceRepository balanceRepository){
        this.balanceRepo = balanceRepository;
    }

    public Balance createBalance(String name) {
        if (balanceRepo.existsByName(name)) {
            throw new RuntimeException("Balance already exists");
        }
        return balanceRepo.save(new Balance(name));
    }

    
}
