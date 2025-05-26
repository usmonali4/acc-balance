package com.up.balance_acc.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.up.balance_acc.entity.Balance;

@Repository
public class BalanceRepository {
    private Map<String, Balance> balances = new HashMap<>();

    public Optional<Balance> findByName(String name) {
        return Optional.ofNullable(balances.get(name));
    }

    public Balance save(Balance balance) {
        balances.put(balance.getName(), balance);
        return balance;
    }

    public boolean existsByName(String name) {
        return balances.containsKey(name);
    }
}
