package controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;


import com.example.bertrandyvernault.cookfridge.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import model.Product;
import view.CustomPopUp;

public class MainActivity extends AppCompatActivity {

    private Button mFridgeButton;
    private Button mAddButton;
    private Button mRemoveButton;
    private Button mTestButton;
    private MainActivity activity;
    private SharedPreferences mPreferences;
    public static ArrayList<Product> mProduct = new ArrayList<>();

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
        mTestButton = (Button) findViewById(R.id.button_test);

        mPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPreferences.getString(ITEM_DATA, null);
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType(); //We specify precisely that Gson should convert it to a List of ItemRowFridge.
        mProduct = gson.fromJson(json, type);

        mFridgeButton.setEnabled(!mProduct.isEmpty());

        //  This here where we handle the access to the list fridge activity
        mFridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listFridgeActivity = new Intent(MainActivity.this, ListFridgeActivity.class);
                startActivity(listFridgeActivity);
            }
        });

        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testActivity = new Intent(MainActivity.this, TestActivity.class);
                startActivity(testActivity);
            }
        });

        //  This here where we handle the access to the add button
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a Pop up Activity to add an product
                final CustomPopUp customPopUp = new CustomPopUp(activity);
                customPopUp.getNameViewEdit().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //Stock the name and amount of the Article (user text input)
                        final String nameArticle = customPopUp.getNameViewEdit().getText().toString();

                        customPopUp.getDoneButton().setEnabled(!nameArticle.isEmpty());
                        customPopUp.getDoneButton().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int amountArticle = (int) Integer.parseInt(customPopUp.getAmountViewEdit().getText().toString());

                                // We add our new product to the list of Item in the fridge
                                mProduct.add(new Product(nameArticle,amountArticle,1,"2018/12/12", "apple.png"));

                                // We save array in shared preferences using Gson library
                                SharedPreferences.Editor editor = mPreferences.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(mProduct);
                                editor.putString(ITEM_DATA, json);
                                editor.apply();

                                customPopUp.dismiss();
                            }
                        });
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

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
