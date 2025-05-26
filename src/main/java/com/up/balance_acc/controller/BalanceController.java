package com.up.balance_acc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up.balance_acc.entity.Balance;
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

    @PostMapping("/{name}")
    public ResponseEntity<Balance> createBalance(@PathVariable String name) {
        return ResponseEntity.ok(balanceService.createBalance(name));
    }
}
