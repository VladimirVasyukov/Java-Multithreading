package com.epam.multithreading.model.factory;

import com.epam.multithreading.model.bank.Bank;
import com.epam.multithreading.model.bank.CitiBank;

public class BankFactory implements Factory<Bank> {
    private static final String NAME = "Bank";
    private static final String INITIAL_MONEY_KEY = "bankInitialMoneyAmount";
    private static int id;

    @Override
    public Bank get() {
        id++;
        return new CitiBank(String.format("%s-%d", NAME, id),
            Integer.parseInt(CONFIGURATION_PARAMETERS.getString(INITIAL_MONEY_KEY)));
    }
}
