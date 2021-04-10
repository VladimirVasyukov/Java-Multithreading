package com.epam.multithreading;

import com.epam.multithreading.controller.Controller;

import java.util.ResourceBundle;

/**
 * 07_Threads - Vladimir Vasyukov
 */

public class Application {
    private static final ResourceBundle CONFIGURATION_PARAMETERS = ResourceBundle.getBundle("configuration_parameters");
    private static final String DAY_TIME_KEY = "workingDayTime";
    private static final String BANK_AMOUNT_KEY = "bankAmount";
    private static final String WORKER_AMOUNT_KEY = "workerAmount";
    private static final String SPENDER_AMOUNT_KEY = "spenderAmount";

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    private void run() {
        Controller controller = new Controller();
        int bankAmount = Integer.parseInt(CONFIGURATION_PARAMETERS.getString(BANK_AMOUNT_KEY));
        int workerAmount = Integer.parseInt(CONFIGURATION_PARAMETERS.getString(WORKER_AMOUNT_KEY));
        int spenderAmount = Integer.parseInt(CONFIGURATION_PARAMETERS.getString(SPENDER_AMOUNT_KEY));
        controller.initBankSystemData(bankAmount, workerAmount, spenderAmount);
        long workingDayTime = Long.parseLong(CONFIGURATION_PARAMETERS.getString(DAY_TIME_KEY));
        controller.startWorking(workingDayTime);
        controller.startMedia();
    }

}
