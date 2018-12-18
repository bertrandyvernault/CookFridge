package controller;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.example.bertrandyvernault.cookfridge.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import model.Product;
import outils.HttpCall;
import outils.HttpRequest;
import view.FridgeItemAdapter;

public class ListFridgeActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    private static final String ITEM_DATA = "MOOD_DATA";

    private SharedPreferences mPreferences;
    private ListView mListView;
    public static ArrayList<Product> mProduct = new ArrayList<>();

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fridge);

        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.GET);
        httpCall.setUrl("http://192.168.43.84:8090/api/product/read.php");
        HashMap<String,String> params = new HashMap<>();
        //params.put("s","tomate");
        httpCall.setParams(params);
        new HttpRequest(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                try{
                    JSONObject object = new JSONObject(response);
                    JSONArray Jarray  = object.getJSONArray("records");

                    for (int i = 0; i < Jarray.length(); i++)
                    {
                        JSONObject Jasonobject = Jarray.getJSONObject(i);
                        Product newProduct = new Product(
                                Jasonobject.getString("name"),
                                Jasonobject.getInt("nombre"),
                                Jasonobject.getInt("nombre"),
                                Jasonobject.getString("date"));
                        boolean test = true;
                        for(Product product : mProduct){
                            // Verifie que le produit n'est pas déjà dans notre Liste
                            if(newProduct.getName().equals(product.getName())){test = false;}
                        }
                        // Si il n'est pas présent on le rajout à notre liste
                        if(test){ mProduct.add(newProduct);}
                    }
                }catch(JSONException e){
                    Log.e("log_tag", "Error parsing data "+e.toString());
                }
                Log.d("STATUS","*******************"+response);
            }
        }.execute(httpCall);

        // get list view
        mListView = findViewById(R.id.list_view);
        mListView.setAdapter(new FridgeItemAdapter(this,mProduct));
    }


}
