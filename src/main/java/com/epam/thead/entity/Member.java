package com.epam.thead.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Member implements Runnable {
    private final static Logger LOGGER = LogManager.getLogger(Member.class);
    private static final Lock LOCK = new ReentrantLock();
    private static final Random RANDOM = new Random();
    private Auction auction;
    private CyclicBarrier barrier;
    private int id;
    private int percentBet;
    private int price;

    public Member(int id, int percentBet){
        this.id = id;
        this.percentBet = percentBet;
        auction = Auction.getInstance();
    }

    @Override
    public void run(){
        try {
            this.barrier = auction.getBarrier();
            int lotSize = auction.getLots().size();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                LOCK.lock();
                int j = RANDOM.nextInt(lotSize);
                int price = auction.getLots().get(j).getStartPrice();
                int delta = percentBet * price / 100;
                price += delta;
                auction.getLots().get(j).setStartPrice(price);
                this.price = price;
                auction.getWinners().put(auction.getLots().get(j).getId(), this);
                LOGGER.info( String.format( "Member № %x make a bid to: %d for the lot № %x", this.id,price,(j+1)));
            }
            this.barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return id == member.id &&
                percentBet == member.percentBet &&
                Objects.equals(auction, member.auction) &&
                Objects.equals(barrier, member.barrier);
    }

    @Override
    public int hashCode(){
        return Objects.hash(auction, barrier, id, percentBet);
    }

    @Override
    public String toString(){
        return "Member{" +
                "id=" + id +
                ", percentBet=" + percentBet +
                ", price=" + price +
                '}';
    }
}
