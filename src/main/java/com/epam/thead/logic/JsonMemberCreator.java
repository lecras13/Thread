package com.epam.thead.logic;

import com.epam.thead.entity.Member;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonMemberCreator implements JsonMember {
    private static final String MEMBERS = "members";
    private static final String ID = "id";
    private static final String PERCENT_BET = "percentBet";

    public List<Member> create(String filePath) {
       List<Member> members = new ArrayList<>();
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) obj;

        JSONArray membersJson = (JSONArray) jo.get(MEMBERS);
        Iterator membersItr = membersJson.iterator();

        while (membersItr.hasNext()) {
            JSONObject test = (JSONObject) membersItr.next();
            String id = (String) test.get(ID);
            String percentBet = (String) test.get(PERCENT_BET);
            Member member = new Member(Integer.parseInt(id), Integer.parseInt(percentBet));
            members.add(member);
        }
        return members;
    }
}