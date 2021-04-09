package com.epam.multithreading.model.factory;

import com.epam.multithreading.model.clients.Prodigal;
import com.epam.multithreading.model.clients.Spender;

public class SpenderFactory implements Factory<Spender> {
    private static final String NAME = "Spender";
    private static final String INITIAL_MONEY_KEY = "clientInitialMoneyAmount";
    private static int id;

    @Override
    public Spender get() {
        id++;
        return new Prodigal(String.format("%s-%d", NAME, id),
            Integer.parseInt(CONFIGURATION_PARAMETERS.getString(INITIAL_MONEY_KEY)));
    }
}
