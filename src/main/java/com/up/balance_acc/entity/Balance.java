package com.up.balance_acc.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Balance {
    @Id
    private String name;
    private double totalAmount = 0.0; //!! USD

    // private List<Transaction> transactions = new ArrayList<>();

    public Balance(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public double getTotalAmount(){
        return totalAmount;
    }
}
