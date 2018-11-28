package model;

public class ItemRowFridge {
    private String mName;
    private int mAmount;
    private String mDate;

    public ItemRowFridge(String pseudo, int score, String date) {
        mName = pseudo;
        mAmount = score;
        mDate = date;
    }

    public String getName() {
        return mName;
    }

    public int getAmount() {
        return mAmount;
    }

    public String getDate() {
        return mDate;
    }
}
