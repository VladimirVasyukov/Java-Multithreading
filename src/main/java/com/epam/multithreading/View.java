package com.epam.multithreading;

import com.epam.multithreading.model.media.Media;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class View {
    private static final Logger LOG = LogManager.getLogger(View.class);
    private static final String DAY_START_MESSAGE = "Total money amount in city on day start: ${}%n";
    private static final String DAY_END_MESSAGE = "Total money amount in city on day end: ${}%n";
    private static final String THREAD_END_MESSAGE = "Thread {} has been stopped.%n";

    public void printDayStartMessage(Media media) {
        LOG.info(DAY_START_MESSAGE, media.getMoneyAmount());
    }

    public void printDayEndMessage(Media media) {
        LOG.info(DAY_END_MESSAGE, media.getMoneyAmount());
    }

    public static void printEndThreadMessage(String threadName) {
        LOG.info(THREAD_END_MESSAGE, threadName);
    }

}
