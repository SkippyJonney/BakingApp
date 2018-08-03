package com.example.jonathan.bakingapp.Data;

import java.util.ArrayList;

public class DummyData {

    // Class of dummy data to populate views with
    ArrayList<String> dummyArray = new ArrayList<>();

    public DummyData() {
        buildArray();
    }

    public ArrayList<String> getData() {
        return dummyArray;
    }

    public String getName(int index) { return dummyArray.get(index);}


    void buildArray() {
        String[] strings = {"Cake", "Pizza", "Dave's Dead Bread", "Pasta Salad Roll", "Space Buns", "Extreme Chocolate"};

        for(int i = 0; i < strings.length; i++) {
            dummyArray.add(strings[i]);
        }
    }
}
