package controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bertrandyvernault.cookfridge.R;

import java.util.ArrayList;

import model.ItemRowFridge;
import view.FridgeAdapter;

public class ListFridgeActivity extends AppCompatActivity {

    public static final String PREF_KEY_AMOUNT = "PKA";
    public static final String PREF_KEY_NAME = "PKN";
    public static final String SHARED_PREFS = "sharedPrefs";
    private SharedPreferences mPreferences;
    private ListView mListView;
    public static ArrayList<ItemRowFridge> mItemRowFridge = new ArrayList<>();
    public static ItemRowFridge mTest1 = new ItemRowFridge("Banane", 4, "12/12/12");
    public static ItemRowFridge mTest2 = new ItemRowFridge("Pomme", 2, "10/12/13");
    public static ItemRowFridge mTest3 = new ItemRowFridge("Poire", 1, "22/02/14");
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fridge);

        mPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        mListView = findViewById(R.id.list_view);
        mTextView = findViewById(R.id.test_text);
        mTextView.setText(mPreferences.getString(PREF_KEY_NAME,null));
        mItemRowFridge.add(mTest1);
        mItemRowFridge.add(mTest2);
        mItemRowFridge.add(mTest3);

        final FridgeAdapter adapter = new FridgeAdapter(this, mItemRowFridge);
//        mListView.setAdapter(adapter);
    }
}
