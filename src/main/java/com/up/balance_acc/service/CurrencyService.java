package com.up.balance_acc.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.up.balance_acc.entity.Currency;

@Service
public class CurrencyService {
    private static final Map<Currency, Double> rates = Map.of(
        Currency.USD, 1.0,
        Currency.EUR, 1.1,
        Currency.BYN, 0.3,
        Currency.RUB, 0.013
    );

    public static double toUSD(double amount, Currency currency){
        return amount*rates.get(currency);
    }
}
