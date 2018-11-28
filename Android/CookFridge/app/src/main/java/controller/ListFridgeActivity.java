package controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import com.example.bertrandyvernault.cookfridge.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import model.ItemRowFridge;
import view.FridgeItemAdapter;

public class ListFridgeActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    private static final String ITEM_DATA = "MOOD_DATA";

    private SharedPreferences mPreferences;
    private ListView mListView;
    public static ArrayList<ItemRowFridge> mItemRowFridge = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fridge);

        //We load array from shared preferences using Gson library
        mPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPreferences.getString(ITEM_DATA, null);
        Type type = new TypeToken<ArrayList<ItemRowFridge>>() {
        }.getType(); //We specify precisely that Gson should convert it to a List of ItemRowFridge.
        mItemRowFridge = gson.fromJson(json, type);

        // get list view
        mListView = findViewById(R.id.list_view);
        mListView.setAdapter(new FridgeItemAdapter(this,mItemRowFridge));

    }
}
