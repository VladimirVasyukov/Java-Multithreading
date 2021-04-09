package com.epam.multithreading.model.clients;

import com.epam.multithreading.View;

public class Miner implements Worker {
    private final String name;
    private volatile int moneyAmount;
    private HelpDesk helpDesk;

    public Miner(String name, int initialMoneyAmount) {
        this.name = name;
        this.moneyAmount = initialMoneyAmount;
        setHelpDesk(OkDesk.getInstance());
    }

    @Override
    public final void setHelpDesk(HelpDesk helpDesk) {
        this.helpDesk = helpDesk;
    }

    @Override
    public synchronized void receiveJobFromSpender(Spender spender) {
        int workerMoneyLimit = helpDesk.getWorkerMoneyLimit();
        try {
            while (moneyAmount >= workerMoneyLimit) {
                wait();
            }
            Thread.sleep(helpDesk.getWorkerWorkingTime());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        receiveMoneyFromSpender(helpDesk.getSalarySize());
    }

    @Override
    public void receiveMoneyFromSpender(int money) {
        moneyAmount += money;
    }

    @Override
    public synchronized void putMoneyToBank() {
        helpDesk.getRandomBank().takeMoney(moneyAmount);
        moneyAmount = 0;
        notifyAll();
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
        int workerMoneyLimit = helpDesk.getWorkerMoneyLimit();
        Thread thread = Thread.currentThread();
        while (!thread.isInterrupted()) {
            while (moneyAmount >= workerMoneyLimit) {
                putMoneyToBank();
            }
            if (thread.isInterrupted()) {
                break;
            }
        }
        View.printEndThreadMessage(name);
    }
}
