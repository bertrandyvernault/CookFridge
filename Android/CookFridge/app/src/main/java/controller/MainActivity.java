package controller;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;


import com.example.bertrandyvernault.cookfridge.R;

import view.CustomPopUp;

public class MainActivity extends AppCompatActivity {

    private Button mFridgeButton;
    private Button mAddButton;
    private Button mRemoveButton;
    private MainActivity activity;
    private SharedPreferences mPreferences;
    private TextView mTextView;

    public static final String PREF_KEY_AMOUNT = "PKA";
    public static final String PREF_KEY_NAME = "PKN";
    public static final String SHARED_PREFS = "sharedPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activity = this;
        mPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        mFridgeButton = (Button) findViewById(R.id.activity_main_bt_fridge);
        mAddButton = (Button) findViewById(R.id.activity_main_bt_add);
        mRemoveButton = (Button) findViewById(R.id.activity_main_bt_rm);
        mTextView = findViewById(R.id.activity_main_text);


        //  This here where we handle the access to the list fridge activity
        mFridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listFridgeActivity = new Intent(MainActivity.this, ListFridgeActivity.class);
                startActivity(listFridgeActivity);
            }
        });

        //  This here where we handle the access to the list fridge activity
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomPopUp customPopUp = new CustomPopUp(activity);
                customPopUp.getDoneButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Stock the name and amount of the Article (user text input) in the shared preferences
                        String nameArticle = customPopUp.getNameViewEdit().getText().toString();
                        String amountArticle = customPopUp.getAmountViewEdit().getText().toString();

                        mPreferences.edit().putString(PREF_KEY_NAME, nameArticle).commit();
                        mPreferences.edit().putString(PREF_KEY_AMOUNT,amountArticle).commit();
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
