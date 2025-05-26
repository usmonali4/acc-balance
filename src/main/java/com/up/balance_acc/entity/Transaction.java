package com.up.balance_acc.entity;

import java.time.LocalDateTime;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;

// @Entity
public class Transaction {
    // @Id
    private int id;

    private TransactionType type;
    private Currency currency;
    private double amount;
    private double usdAmount;
    private LocalDateTime timestamp;
    private String balanceName;

    public Transaction(TransactionType type, double amount, double usdAmount, Currency currency, String balanceName) {
        this.type = type;
        this.amount = amount;
        this.usdAmount = usdAmount;
        this.currency = currency;
        this.timestamp = LocalDateTime.now();
        this.balanceName = balanceName;
    }

    public int getId() { return id; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public double getUSDAmount() { return usdAmount; }
    public Currency getCurrency() { return currency; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getBalanceName() { return balanceName; }

    public void setId(int id){
        this.id = id;
    }
    public void update(TransactionType type, double amount, Currency currency, double amountInUsd) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.usdAmount = amountInUsd;
        this.timestamp = LocalDateTime.now();
    } 
}
