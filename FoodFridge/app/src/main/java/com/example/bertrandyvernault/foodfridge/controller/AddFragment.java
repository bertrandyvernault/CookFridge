package com.example.bertrandyvernault.foodfridge.controller;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.bertrandyvernault.foodfridge.R;
import com.example.bertrandyvernault.foodfridge.model.Product;
import com.example.bertrandyvernault.foodfridge.outils.HttpCall;
import com.example.bertrandyvernault.foodfridge.outils.HttpRequest;
import com.example.bertrandyvernault.foodfridge.view.AddItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment implements AddMessageFragment.ExampleDialogListener {


    private GridView mGridView;
    public static ArrayList<Product> mProductAll = new ArrayList<>();
    Product product;



    public AddFragment() {
        // Required empty public constructor
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add, container, false);

        Log.d("ADD","*************");

        // On recupere tout les produits de la BBD et on les affiches dans une GridView
        final HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.GET);
        httpCall.setUrl("http://192.168.43.84:8090/api/product/readAll.php");
        final HashMap<String, String> params = new HashMap<>();
        httpCall.setParams(params);
        new HttpRequest() {
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray Jarray = object.getJSONArray("records");

                    // On parcourt le Json pour recupere les differents produits de la BBD
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject Jasonobject = Jarray.getJSONObject(i);
                        Product newProduct = new Product(
                                Jasonobject.getInt("id"),
                                Jasonobject.getString("name"),
                                Jasonobject.getInt("nombre"),
                                Jasonobject.getInt("category"),
                                Jasonobject.getString("date"));

                        boolean test = true;

                        for (Product product : mProductAll) {
                            // Verifie que le produit n'est pas déjà dans notre Liste
                            if (newProduct.getName().equals(product.getName())) {
                                test = false;
                            }
                        }
                        // Si il n'est pas présent on le rajout à notre liste
                        if (test) {
                            mProductAll.add(newProduct);
                        }
                    }


                    // get list view & On affiche
                    mGridView = (GridView) view.findViewById(R.id.grid_view);
                    mGridView.setAdapter(new AddItemAdapter(getContext(), mProductAll));


                    // On capte quand l'utilisateur clique sur un produit
                    mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            // Recupere le nom de l'item selectionné
                            Object item = adapterView.getItemAtPosition(position);
                            product = (Product) item;

                            showAlertDialog();

                        }
                    });

                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                }

            }
        }.execute(httpCall);


        // Inflate the layout for this fragment
        return view;
    }

    private void showAlertDialog() {
        AddMessageFragment dialogue = new AddMessageFragment();
        dialogue.setTargetFragment(this, 1);
        dialogue.show(getFragmentManager().beginTransaction(), "AddDialog");

    }

    @Override
    public void applyTexts(String nombre, String date) {
        // Recupere le nombre et la date rentre par l'utilisateur
        Log.d("ICI","*****************");

        // Inflate the layout for this fragment
        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.GET);
        httpCall.setUrl("http://192.168.43.84:8090/api/product/ajout.php");
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(product.getId()));
        params.put("nombre",nombre);
        params.put("date",date);
        httpCall.setParams(params);
        new HttpRequest() {
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                Log.d("SUCCES", "**************"+response);
            }
        }.execute(httpCall);

        Toast toast = Toast.makeText(getActivity(), "SUCESS", Toast.LENGTH_SHORT);
        toast.show();
    }

}
