package com.up.balance_acc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.up.balance_acc.entity.Balance;
import com.up.balance_acc.entity.Transaction;
import com.up.balance_acc.entity.TransactionDTO;
import com.up.balance_acc.repository.BalanceRepository;
import com.up.balance_acc.repository.TransactionRepository;

@Service
public class BalanceService {
    private final BalanceRepository balanceRepo;
    private final TransactionRepository txRepo;

    public BalanceService(BalanceRepository balanceRepository, TransactionRepository transactionRepository){
        this.balanceRepo = balanceRepository;
        this.txRepo = transactionRepository;
    }

    public Balance createBalance(String name) {
        if (balanceRepo.existsByName(name)) {
            throw new RuntimeException("Balance already exists");
        }
        return balanceRepo.save(new Balance(name));
    }

    public Transaction addTransaction(String name, TransactionDTO dto) {
        Balance balance = balanceRepo.findByName(name)
            .orElseThrow(() -> new RuntimeException("Balance not found"));

        double usdAmount = CurrencyService.toUSD(dto.amount, dto.currency);
        Transaction tx = new Transaction(dto.type, dto.amount, usdAmount, dto.currency, name);
        balance.applyTransaction(tx);
        txRepo.save(tx);
        return tx;
    }

    public Balance getBalance(String name) {
        Balance balance = balanceRepo.findByName(name)
            .orElseThrow(() -> new RuntimeException("Balance not found"));
        
        return balance;
    }

    public List<Transaction> allTransactions(String name){
        Balance balance = balanceRepo.findByName(name)
            .orElseThrow(() -> new RuntimeException("Balance not found"));

        return balance.geTransactions();
    }

    public Transaction updTransaction(int id, TransactionDTO dto){
        Transaction tx = txRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Balance balance = balanceRepo.findByName(tx.getBalanceName())
            .orElseThrow(() -> new RuntimeException("Balance not found"));
        
        balance.reverseTransaction(tx);
        double amountInUsd = CurrencyService.toUSD(dto.amount, dto.currency);

        tx.update(dto.type, dto.amount, dto.currency, amountInUsd);
        balance.applyTransaction(tx);
        return tx;
    }

    
}
