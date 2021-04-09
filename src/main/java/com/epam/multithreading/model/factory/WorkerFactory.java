package com.epam.multithreading.model.factory;

import com.epam.multithreading.model.clients.Miner;
import com.epam.multithreading.model.clients.Worker;

public class WorkerFactory implements Factory<Worker> {
    private static final String NAME = "Worker";
    private static final String INITIAL_MONEY_KEY = "clientInitialMoneyAmount";
    private static int id;

    @Override
    public Worker get() {
        id++;
        return new Miner(String.format("%s-%d", NAME, id),
            Integer.parseInt(CONFIGURATION_PARAMETERS.getString(INITIAL_MONEY_KEY)));
    }
}
