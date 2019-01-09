package com.example.bertrandyvernault.foodfridge.controller;


import android.accessibilityservice.GestureDescription;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bertrandyvernault.foodfridge.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements AddMessageFragment.ExampleDialogListener {

    private Button mButton;
    private TextView mNombre, mDate;
    private static final int DIALOG_ALERT = 10;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_account, container, false);

        mNombre = view.findViewById(R.id.textViewNombre);
        mDate = view.findViewById(R.id.textViewDate);
        mButton = view.findViewById(R.id.button);
        mNombre.setText("NOMBRE");
        mDate.setText("DATE");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        return  view;

    }

    public void openDialog() {


        AddMessageFragment exampleDialog = new AddMessageFragment();
        exampleDialog.setTargetFragment(this, 1);
        exampleDialog.show(getFragmentManager().beginTransaction(), "AddDialog");
    }

    @Override
    public void applyTexts(String nombre, String date) {
        mNombre.setText(nombre);
        mDate.setText(date);
    }

}

