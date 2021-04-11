package com.epam.multithreading.model.clients;

import com.epam.multithreading.model.bank.Bank;
import com.epam.multithreading.model.media.Wiretapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public final class OkDesk implements HelpDesk {
    private static final ResourceBundle CONFIGURATION_PARAMETERS = ResourceBundle.getBundle("configuration_parameters");
    private static final String WORKER_WORKING_TIME_KEY = "workerWorkingTime";
    private static final String WORKER_MONEY_LIMIT_KEY = "workerMoneyLimit";
    private static final String SALARY_SIZE_KEY = "salarySize";
    private static final String LUNCH_TIME_KEY = "lunchTime";
    private static final String CREDIT_SIZE_KEY = "creditSize";
    private static final Random RANDOM = new Random();
    private static OkDesk okDesk;
    private List<Bank> bankList;
    private List<Worker> workerList;

    private OkDesk() {

    }

    public synchronized static OkDesk getInstance() {
        if (okDesk == null) {
            okDesk = new OkDesk();
        }
        return okDesk;
    }

    private <T extends Wiretapping && Runnable> T generateRandom(List<T> tList) {
        return tList.get(RANDOM.nextInt(tList.size()));
    }

    @Override
    public Long getWorkerWorkingTime() {
        return Long.parseLong(CONFIGURATION_PARAMETERS.getString(WORKER_WORKING_TIME_KEY));
    }

    @Override
    public int getWorkerMoneyLimit() {
        return Integer.parseInt(CONFIGURATION_PARAMETERS.getString(WORKER_MONEY_LIMIT_KEY));
    }

    @Override
    public int getSalarySize() {
        return Integer.parseInt(CONFIGURATION_PARAMETERS.getString(SALARY_SIZE_KEY));
    }

    @Override
    public Bank getRandomBank() {
        return generateRandom(bankList);
    }

    @Override
    public Worker getRandomWorker() {
        return generateRandom(workerList);
    }

    @Override
    public long getLunchTime() {
        return Long.parseLong(CONFIGURATION_PARAMETERS.getString(LUNCH_TIME_KEY));
    }

    @Override
    public int getCreditSize() {
        return Integer.parseInt(CONFIGURATION_PARAMETERS.getString(CREDIT_SIZE_KEY));
    }

    @Override
    public void setBankList(List<Bank> bankList) {
        this.bankList = new ArrayList<>(bankList);
    }

    @Override
    public void setWorkers(List<Worker> workers) {
        this.workerList = new ArrayList<>(workers);
    }

}
