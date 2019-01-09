package com.example.bertrandyvernault.foodfridge.controller;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bertrandyvernault.foodfridge.R;
import com.example.bertrandyvernault.foodfridge.model.Product;
import com.example.bertrandyvernault.foodfridge.outils.HttpCall;
import com.example.bertrandyvernault.foodfridge.outils.HttpRequest;
import com.example.bertrandyvernault.foodfridge.view.AddItemAdapter;
import com.example.bertrandyvernault.foodfridge.view.FridgeItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FridgeFragment extends Fragment {

    private GridView mGridView;
    public static ArrayList<Product> mProduct = new ArrayList<>();
    public static ArrayList<Product> mProductLegume = new ArrayList<>();
    public static ArrayList<Product> mProductLait = new ArrayList<>();
    public static ArrayList<Product> mProductBoucherie = new ArrayList<>();
    public static ArrayList<Product> mProductPoisson = new ArrayList<>();
    public static ArrayList<Product> mProductSauce = new ArrayList<>();
    public static ArrayList<Product> mProductVins = new ArrayList<>();
    public static ArrayList<Product> mProductSurgele = new ArrayList<>();

    public static ArrayList<ArrayList<Product>> superListProduct = new ArrayList<ArrayList<Product>>();

    public FridgeFragment() {
        // Required empty public constructor
    }


    @SuppressLint({"StaticFieldLeak", "WrongViewCast"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_fridge, container, false);

        Log.d("FRIDGE","*************");

        mGridView = view.findViewById(R.id.grid_view);
        FridgeItemAdapter adapter = new FridgeItemAdapter(getContext(), mProduct);
        adapter.clearData();

        // Creation de la liste de liste produit
        superListProduct.add(mProductLegume);
        superListProduct.add(mProductLait);
        superListProduct.add(mProductBoucherie);
        superListProduct.add(mProductPoisson);
        superListProduct.add(mProductSauce);
        superListProduct.add(mProductVins);
        superListProduct.add(mProductSurgele);

        // Inflate the layout for this fragment
        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.GET);
        httpCall.setUrl("http://192.168.43.84:8090/api/product/read.php");
        HashMap<String, String> params = new HashMap<>();
        //params.put("s","tomate");
        httpCall.setParams(params);
        new HttpRequest() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray Jarray = object.getJSONArray("records");

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject Jasonobject = Jarray.getJSONObject(i);
                        Product newProduct = new Product(
                                Jasonobject.getInt("id"),
                                Jasonobject.getString("name"),
                                Jasonobject.getInt("nombre"),
                                Jasonobject.getInt("category"),
                                Jasonobject.getString("date"));
                        boolean test = true;
                        if(mProduct != null){
                            for (Product product : mProduct) {
                                // Verifie que le produit n'est pas déjà dans notre Liste
                                if (newProduct.getName().equals(product.getName())) {
                                    test = false;
                                }
                            }
                        }
                        // Si il n'est pas présent on le rajout à notre liste
                        if (test) {
                            mProduct.add(newProduct);
                        }

                    }

                    // get list view & On affiche
                    mGridView.setAdapter(new FridgeItemAdapter(getContext(),mProduct));


                    /**
                     * Plusieurs grid view dans le xml
                     * "@+id/grid_view1" jusqu'à 6
                    Log.d("LIST","**************"+mProduct);
                    int i = 0;
                    for (ArrayList<Product> Listproduit : superListProduct){
                        i = i + 1;
                        String gridViewId = "grid_view"+i;
                        int resId = getResources().getIdentifier(gridViewId,"id", getActivity().getPackageName());
                        mGridView = view.findViewById(resId);
                        FridgeItemAdapter adapter = new FridgeItemAdapter(getContext(), Listproduit);
                        adapter.clearData();


                        for(Product produit : mProduct){
                            if(produit.getCategory() == i){
                                Listproduit.add(produit);
                            }
                        }
                        mGridView.setAdapter(adapter);
                    }
                     **/


                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                }

            }
        }.execute(httpCall);



        return view;
    }
}
