package com.up.balance_acc.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.up.balance_acc.entity.Transaction;

@Repository
public class TransactionRepository {
    private final Map<Integer, Transaction> transactions = new HashMap<>();

    public Transaction save(Transaction tx) {
        int new_id = transactions.size() + 1;
        tx.setId(new_id);
        transactions.put(tx.getId(), tx);
        return tx;
    }

    public Optional<Transaction> findById(UUID id) {
        return Optional.ofNullable(transactions.get(id));
    }

    public List<Transaction> findAllByBalanceName(String balanceName) {
        return transactions.values().stream()
            .filter(t -> t.getBalanceName().equals(balanceName))
            .collect(Collectors.toList());
    }
}
