package com.epam.multithreading.controller;

import com.epam.multithreading.model.bank.Bank;
import com.epam.multithreading.model.clients.HelpDesk;
import com.epam.multithreading.model.clients.Spender;
import com.epam.multithreading.model.clients.Worker;
import com.epam.multithreading.model.media.Media;
import com.epam.multithreading.model.media.Wiretapping;

import java.util.List;

public class Controller implements InitHelper {
    @Override
    public List<Bank> createBanks(int bankAmount) {
        return null;
    }

    @Override
    public List<Worker> createWorkers(int workerAmount) {
        return null;
    }

    @Override
    public List<Spender> createSpenders(int spenderAmount) {
        return null;
    }

    @Override
    public Media createMedia(List<Wiretapping> sneakyObjects) {
        return null;
    }

    @Override
    public HelpDesk createHelpDesk() {
        return null;
    }

    @Override
    public void initBankSystemData(int bankAmount, int workerAmount, int spenderAmount) {

    }

    @Override
    public void startWorking(long workingTime) {

    }

    @Override
    public Media getMedia() {
        return null;
    }
}
