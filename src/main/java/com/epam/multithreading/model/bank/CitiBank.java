package com.epam.multithreading.model.bank;

import com.epam.multithreading.View;
import com.epam.multithreading.model.clients.HelpDesk;
import com.epam.multithreading.model.clients.OkDesk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CitiBank implements Bank {
    private static final long BANK_WORKING_TIME = 1000L;
    private static final Logger LOG = LogManager.getLogger(View.class);
    private final String name;
    private volatile int moneyAmount;
    private HelpDesk helpDesk;

    public CitiBank(String name, int initialMoneyAmount) {
        this.name = name;
        this.moneyAmount = initialMoneyAmount;
        setHelpDesk(OkDesk.getInstance());
    }

    private void acceptClients() {
        try {
            Thread.sleep(BANK_WORKING_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public synchronized void takeMoney(int money) {
        moneyAmount += money;
    }

    @Override
    public synchronized int giveMoney() {
        int credit = helpDesk.getCreditSize();
        moneyAmount -= credit;
        return credit;
    }

    @Override
    public synchronized void goToLunch() {
        try {
            Thread.sleep(helpDesk.getLunchTime());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.error("Unexpected InterruptedException in goToLunch method!", e);
        }
    }

    @Override
    public final void setHelpDesk(HelpDesk helpDesk) {
        this.helpDesk = helpDesk;
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
        Thread thread = Thread.currentThread();
        while (!thread.isInterrupted()) {
            acceptClients();
            goToLunch();
            if (thread.isInterrupted()) {
                break;
            }
        }
        View.printEndThreadMessage(name);
    }
}
