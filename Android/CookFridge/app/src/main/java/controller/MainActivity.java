package controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;


import com.example.bertrandyvernault.cookfridge.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import model.ItemRowFridge;
import view.CustomPopUp;

public class MainActivity extends AppCompatActivity {

    private Button mFridgeButton;
    private Button mAddButton;
    private Button mRemoveButton;
    private MainActivity activity;
    private SharedPreferences mPreferences;
    public static ArrayList<ItemRowFridge> mItemRowFridge = new ArrayList<>();

    public static final String SHARED_PREFS = "sharedPrefs";
    private static final String ITEM_DATA = "MOOD_DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activity = this;

        mPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        mFridgeButton = (Button) findViewById(R.id.activity_main_bt_fridge);
        mAddButton = (Button) findViewById(R.id.activity_main_bt_add);
        mRemoveButton = (Button) findViewById(R.id.activity_main_bt_rm);


        //  This here where we handle the access to the list fridge activity
        mFridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listFridgeActivity = new Intent(MainActivity.this, ListFridgeActivity.class);
                startActivity(listFridgeActivity);
            }
        });

        //  This here where we handle the access to the add button
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a Pop up Activity to add an product
                final CustomPopUp customPopUp = new CustomPopUp(activity);
                customPopUp.getDoneButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Stock the name and amount of the Article (user text input)
                        String nameArticle = customPopUp.getNameViewEdit().getText().toString();
                        int amountArticle = (int) Integer.parseInt(customPopUp.getAmountViewEdit().getText().toString());

                        // We add our new product to the list of Item in the fridge
                        mItemRowFridge.add(new ItemRowFridge(nameArticle,amountArticle));

                        // We save array in shared preferences using Gson library
                        SharedPreferences.Editor editor = mPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(mItemRowFridge);
                        editor.putString(ITEM_DATA, json);
                        editor.apply();

                        customPopUp.dismiss();
                    }
                });
                customPopUp.getCancelButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customPopUp.dismiss();
                    }
                });
                customPopUp.build();

            }
        });

    }
}
