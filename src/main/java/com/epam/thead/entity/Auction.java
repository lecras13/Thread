package com.epam.thead.entity;

import com.epam.thead.logic.JsonLot;
import com.epam.thead.logic.JsonLotCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Auction {
    private static final Logger LOGGER = LogManager.getLogger(Auction.class);
    private static final String LOTS_PATH = "src/main/resources/lots.json";
    private static final JsonLot JSON_CREATOR = new JsonLotCreator();
    private static final Lock LOCK = new ReentrantLock();
    private static final int BIDS_NUMBER = 5;
    private static Auction INSTANCE;

    private CyclicBarrier barrier;
    private List<Lot> lots;
    private Map<Integer, Member> winners;

    private Auction(){
        this.lots = JSON_CREATOR.create(LOTS_PATH);
        this.winners = new HashMap<>();
        this.barrier = new CyclicBarrier(this.BIDS_NUMBER, new Runnable() {
            public void run(){
                LOGGER.info("Winners are:");
                printWinners(winners);
            }
        });
    }

    public static Auction getInstance(){
        try {
            if (INSTANCE == null) {
                LOCK.lock();
                Auction localINSTANCE;
                if (INSTANCE == null) {
                    localINSTANCE = new Auction();
                    INSTANCE = localINSTANCE;
                }
            }
        } finally {
            LOCK.unlock();
        }
        return INSTANCE;
    }

    public Map<Integer, Member> getWinners(){
        return winners;
    }

    public List<Lot> getLots(){
        return lots;
    }

    public CyclicBarrier getBarrier(){
        return barrier;
    }

    private static void printWinners(Map<Integer, Member> map){
        for (Map.Entry<Integer, Member> pair : map.entrySet()) {
            Integer key = pair.getKey();
            Member value = pair.getValue();
            LOGGER.info(String.format("Lot number № %s : %s", key, value.toString()));
        }
    }
}
