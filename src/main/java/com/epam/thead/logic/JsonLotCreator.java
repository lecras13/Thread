package com.epam.thead.logic;

import com.epam.thead.entity.Lot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonLotCreator implements JsonLot {
    private static final String LOTS = "lots";
    private static final String ID = "id";
    private static final String START_PRICE = "startPrice";

    public List<Lot> create(String filePath){
        List<Lot> lots = new ArrayList<>();
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) obj;

        JSONArray lotsJson = (JSONArray) jo.get(LOTS);
        Iterator lotsItr = lotsJson.iterator();

        while (lotsItr.hasNext()) {
            JSONObject test = (JSONObject) lotsItr.next();
            String id = (String) test.get(ID);
            String startPrice = (String) test.get(START_PRICE);
            Lot lot = new Lot(Integer.parseInt(id), Integer.parseInt(startPrice));
            lots.add(lot);
        }
        return lots;
    }
}