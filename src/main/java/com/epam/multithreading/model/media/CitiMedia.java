package com.epam.multithreading.model.media;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CitiMedia implements Media {
    private static final ResourceBundle CONFIGURATION_PARAMETERS = ResourceBundle.getBundle("configuration_parameters");
    private static final Logger LOG = LogManager.getLogger(CitiMedia.class);
    private static final String LUNCH_TIME_KEY = "lunchTime";
    private static final String GOOD_NEWS_MESSAGE = "Good news for everyone! Total amount money in city is: $%d%n";
    private static final String HAS_MONEY_MESSAGE = "This %s has money: $%d%n";
    private final List<? extends Wiretapping> wiretappingList;

    public CitiMedia(List<? extends Wiretapping> wiretappingList) {
        this.wiretappingList = new ArrayList<>(wiretappingList);
    }

    private void goToLunch() {
        try {
            Thread.sleep(Long.parseLong(CONFIGURATION_PARAMETERS.getString(LUNCH_TIME_KEY)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void printInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(GOOD_NEWS_MESSAGE, getMoneyAmount()));
        for (Wiretapping wiretapping : wiretappingList) {
            stringBuilder.append(String.format(HAS_MONEY_MESSAGE, wiretapping.getName(), wiretapping.getMoneyAmount()));
        }
        LOG.info(stringBuilder);
    }

    @Override
    public int getMoneyAmount() {
        int totalMoneyAmount = 0;
        for (Wiretapping wiretapping : wiretappingList) {
            totalMoneyAmount += wiretapping.getMoneyAmount();
        }
        return totalMoneyAmount;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        while (!thread.isInterrupted()) {
            printInfo();
            goToLunch();
            if (thread.isInterrupted()) {
                break;
            }
        }
    }

}
