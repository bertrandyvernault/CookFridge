package model;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String mName;
    private int mCategory;
    private int mAmount;
    private String mDate;

    public Product(String pseudo, int amount, int category, String date) {
        this.mName = pseudo;
        this.mAmount = amount;
        this.mCategory = category;
        this.mDate = date;
    }

    public String getName() { return mName; }

    public void setName(String name) { mName = name; }

    public int getCategory() { return mCategory; }

    public void setCategory(int category) { this.mCategory = category; }

    public int getAmount() { return mAmount; }

    public void setAmount(int amount) { mAmount = amount; }

    public String getDate() { return mDate; }

    public void setDate(String date) { mDate =date; }

    /**
     * conversion du product au format jsonArray
     * @return
     */
    public JSONArray convertToJsonArray(){
        List LaListe = new ArrayList();
        LaListe.add(mName);
        LaListe.add(mCategory);
        LaListe.add(mAmount);
        LaListe.add(mDate);
        return new JSONArray(LaListe);

    }
}
