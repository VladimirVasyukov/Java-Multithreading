package com.epam.multithreading.model.clients;

import com.epam.multithreading.View;

public class Prodigal implements Spender {
    private HelpDesk helpDesk;
    private final String name;
    private int moneyAmount;

    public Prodigal(String name, int initialMoneyAmount) {
        this.name = name;
        this.moneyAmount = initialMoneyAmount;
        setHelpDesk(OkDesk.getInstance());
    }

    @Override
    public final void setHelpDesk(HelpDesk helpDesk) {
        this.helpDesk = helpDesk;
    }

    @Override
    public void hireWorker() {
        helpDesk.getRandomWorker().receiveJobFromSpender(this);
        payMoneyToWorker();
    }

    @Override
    public void payMoneyToWorker() {
        moneyAmount -= helpDesk.getSalarySize();
    }

    @Override
    public void getMoneyFromBank() {
        moneyAmount += helpDesk.getRandomBank().giveMoney();
    }

    @Override
    public int getMoneyAmount() {
        return moneyAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        int salary = helpDesk.getSalarySize();
        Thread thread = Thread.currentThread();
        while (!thread.isInterrupted()) {
            if (moneyAmount >= salary) {
                hireWorker();
            } else {
                getMoneyFromBank();
            }
            if (thread.isInterrupted()) {
                break;
            }
        }
        View.printEndThreadMessage(name);
    }
}
