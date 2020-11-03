package com.epam.thead;

import com.epam.thead.logic.JsonMemberCreator;
import com.epam.thead.entity.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.*;


public class Director {
    private final static Logger LOGGER = LogManager.getLogger(Director.class);
    private static final String MEMBERS_PATH = "src/main/resources/members.json";

    public static void main(String[] args){
        LOGGER.info("Auction starts!");
        process();
    }

    private static void process(){
        JsonMemberCreator jsonMemberCreator = new JsonMemberCreator();
        List<Member> members = jsonMemberCreator.create(MEMBERS_PATH);
        ExecutorService service = Executors.newFixedThreadPool(members.size());
        members.forEach(service::submit);
        service.shutdown();
    }
}
