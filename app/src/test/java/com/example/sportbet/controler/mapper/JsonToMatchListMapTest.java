package com.example.sportbet.controler.mapper;

import static org.junit.Assert.assertEquals;

import com.example.sportbet.controler.exception.InvalidResourcesException;
import com.example.sportbet.model.match.Match;

import org.json.JSONException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonToMatchListMapTest {
    private final String PATH_PREFIX = "src\\test\\java\\com\\example\\sportbet\\controler\\mapper\\";

    @Test
    public void mapEveryThingDataTest() throws JSONException, InvalidResourcesException {
        var matchList =
                JsonToMatchListMap.mapJsonToMatchList(readTestData("jsonInputOneMatch.json"));
        assertEquals(1, matchList.size());
    }

    private String readTestData(String fileName) {
        var data = new StringBuilder();
        try {
            File myObj = new File(PATH_PREFIX + fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found");
            e.printStackTrace();
        }
        return String.valueOf(data);
    }
}