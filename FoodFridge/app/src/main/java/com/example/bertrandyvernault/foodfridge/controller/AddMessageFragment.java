package com.example.bertrandyvernault.foodfridge.controller;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.bertrandyvernault.foodfridge.R;

import java.util.Calendar;

public class AddMessageFragment extends AppCompatDialogFragment {

    private EditText mNombre, mDate;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_message_layout, null);
        mNombre = view.findViewById(R.id.nombre_view);
        mDate = view.findViewById(R.id.date_view);

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String mois = null;
                                if (monthOfYear < 10) {
                                    mois = "0" + (monthOfYear + 1);
                                }else{
                                    mois = String.valueOf((monthOfYear + 1));
                                }
                                mDate.setText(year + mois + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        builder.setView(view)
                .setTitle("Add Product")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = mNombre.getText().toString();
                        String password = mDate.getText().toString();
                        listener.applyTexts(username, password);
                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (ExampleDialogListener) getTargetFragment();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface ExampleDialogListener {
        void applyTexts(String nombre, String date);
    }
}