package model;

public class ItemRowFridge {
    private String mName;
    private int mAmount;

    public ItemRowFridge(String pseudo, int score) {
        this.mName = pseudo;
        this.mAmount = score;
    }

    public String getName() {
        return mName;
    }

    public int getAmount() {
        return mAmount;
    }
}
