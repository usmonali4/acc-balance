package com.up.balance_acc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up.balance_acc.entity.Balance;
import com.up.balance_acc.entity.Transaction;
import com.up.balance_acc.entity.TransactionDTO;
import com.up.balance_acc.service.BalanceService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/balances")
public class BalanceController {
    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }

    @GetMapping("/{name}/info")
    public ResponseEntity<Balance> getBalance(@PathVariable String name){
        return ResponseEntity.ok(balanceService.getBalance(name));
    }

    @PostMapping("/{name}")
    public ResponseEntity<Balance> createBalance(@PathVariable String name) {
        return ResponseEntity.ok(balanceService.createBalance(name));
    }

    @PostMapping("/{name}/add/transaction")
    public ResponseEntity<Transaction> addTransaction(@PathVariable String name, @RequestBody TransactionDTO dto) {
        return ResponseEntity.ok(balanceService.addTransaction(name, dto));
    }

    @GetMapping("/{name}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String name){
        return ResponseEntity.ok(balanceService.allTransactions(name));
    }

}
