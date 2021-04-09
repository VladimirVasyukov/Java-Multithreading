package com.epam.multithreading.controller;

import com.epam.multithreading.View;
import com.epam.multithreading.model.bank.Bank;
import com.epam.multithreading.model.clients.HelpDesk;
import com.epam.multithreading.model.clients.Spender;
import com.epam.multithreading.model.clients.Worker;
import com.epam.multithreading.model.factory.BankFactory;
import com.epam.multithreading.model.factory.Factory;
import com.epam.multithreading.model.clients.OkDesk;
import com.epam.multithreading.model.factory.SpenderFactory;
import com.epam.multithreading.model.factory.WorkerFactory;
import com.epam.multithreading.model.media.Media;
import com.epam.multithreading.model.media.Wiretapping;
import com.epam.multithreading.model.media.CitiMedia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Controller implements InitHelper {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private static final String THREAD_GROUP_NAME = "Working threads";
    private final List<Wiretapping> wiretappingList = new ArrayList<>();
    private final View view;
    private Media media;

    public Controller() {
        this.media = null;
        this.view = new View();
    }

    private <T extends Wiretapping> List<T> generateList(Factory<T> factory, int amount) {
        List<T> tList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            tList.add(factory.get());
        }
        return tList;
    }

    @Override
    public List<Bank> createBanks(int bankAmount) {
        return generateList(new BankFactory(), bankAmount);
    }

    @Override
    public List<Worker> createWorkers(int workerAmount) {
        return generateList(new WorkerFactory(), workerAmount);
    }

    @Override
    public List<Spender> createSpenders(int spenderAmount) {
        return generateList(new SpenderFactory(), spenderAmount);
    }

    @Override
    public Media createMedia(List<Wiretapping> sneakyObjects) {
        return new CitiMedia(sneakyObjects);
    }

    @Override
    public HelpDesk createHelpDesk() {
        return OkDesk.getInstance();
    }

    @Override
    public final void initBankSystemData(int bankAmount, int workerAmount, int spenderAmount) {
        HelpDesk helpDesk = createHelpDesk();
        List<Bank> bankList = createBanks(bankAmount);
        wiretappingList.addAll(bankList);
        helpDesk.setBankList(bankList);

        List<Worker> workerList = createWorkers(workerAmount);
        wiretappingList.addAll(workerList);
        helpDesk.setWorkers(workerList);

        List<Spender> spenderList = createSpenders(spenderAmount);
        wiretappingList.addAll(spenderList);

        media = createMedia(wiretappingList);
    }

    @Override
    public void startWorking(long workingTime) {
        view.printDayStartMessage(media);

        ThreadGroup workingThreadGroup = new ThreadGroup(THREAD_GROUP_NAME);
        for (Wiretapping wiretapping : wiretappingList) {
            new Thread(workingThreadGroup, (Runnable) wiretapping, wiretapping.getName()).start();
        }

        Thread mediaThread = new Thread(getMedia());
        mediaThread.setDaemon(true);
        mediaThread.start();

        try {
            Thread.sleep(workingTime);

            mediaThread.interrupt();
            workingThreadGroup.interrupt();

            Thread[] workingThreads = new Thread[wiretappingList.size()];
            workingThreadGroup.enumerate(workingThreads);
            for (Thread thread : workingThreads) {
                if (thread != null) {
                    thread.join();
                }
            }
        } catch (InterruptedException interruptedException) {
            LOG.error("Unexpected InterruptedException in the startWorking run method!", interruptedException);
        }

        view.printDayEndMessage(media);
    }

    @Override
    public Media getMedia() {
        return media;
    }

}
