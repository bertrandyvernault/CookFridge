package com.example.bertrandyvernault.foodfridge.model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private int category;
    private int amount;
    private String date;

    public Product(int id, String pseudo, int amount, int category, String date) {
        this.id = id;
        this.name = pseudo;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCategory() { return category; }

    public void setCategory(int category) { this.category = category; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date =date; }

    /**
     * conversion du product au format jsonArray
     * @return
     */
    public JSONArray convertToJsonArray(){
        List laListe = new ArrayList();
        laListe.add(name);
        laListe.add(category);
        laListe.add(amount);
        laListe.add(date);
        return new JSONArray(laListe);

    }
}
