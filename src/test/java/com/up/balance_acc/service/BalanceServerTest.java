package com.up.balance_acc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.up.balance_acc.entity.Balance;
import com.up.balance_acc.entity.Currency;
import com.up.balance_acc.entity.Transaction;
import com.up.balance_acc.entity.TransactionDTO;
import com.up.balance_acc.entity.TransactionType;
import com.up.balance_acc.repository.BalanceRepository;
import com.up.balance_acc.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {

    @Mock
    private BalanceRepository balanceRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private BalanceService balanceService;

    @Test
    void testCreateBalance() {
        String name = "main-acc";

        when(balanceRepository.existsByName(name)).thenReturn(false);
        when(balanceRepository.save(any(Balance.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Balance created = balanceService.createBalance(name);

        assertEquals(name, created.getName());
        assertEquals(0.0, created.getTotalAmount());
    }

    @Test
    void testAddTransactionDeposit() {
        String name = "main-acc";
        Balance balance = new Balance(name);
        TransactionDTO dto = new TransactionDTO(TransactionType.DEPOSIT, 100.0, Currency.EUR);

        when(balanceRepository.findByName(name)).thenReturn(Optional.of(balance));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Transaction tx = balanceService.addTransaction(name, dto);

        assertEquals(TransactionType.DEPOSIT, tx.getType());
        assertEquals(100.0, tx.getAmount());
        assertEquals(Currency.EUR, tx.getCurrency());
        assertEquals(110, Math.floor(balance.getTotalAmount()));
    }

    @Test
    void testGetBalanceThrowsIfNotFound() {
        when(balanceRepository.findByName("x")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> balanceService.getBalance("x"));
    }
}

