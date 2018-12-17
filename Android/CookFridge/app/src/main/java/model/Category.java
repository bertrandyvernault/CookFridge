package model;

public class Category {
    private int mId;
    private String mName;
    private String mImage;

    public Category(int id, String name, String image) {
        this.mId = id;
        this.mName = name;
        this.mImage = image;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
