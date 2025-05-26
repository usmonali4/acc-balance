package com.up.balance_acc.entity;


public class TransactionDTO {
    public TransactionType type;
    public double amount;
    public Currency currency;

    public TransactionDTO(TransactionType type, double amount, Currency currency){
        this.type = type;
        this.amount = amount;
        this.currency = currency;
    }
}
