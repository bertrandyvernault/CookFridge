package controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.bertrandyvernault.cookfridge.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import outils.HttpCall;
import outils.HttpRequest;


public class TestActivity extends AppCompatActivity {

    private TextView mNameView, mCategoryView, mDateView, mIdView, mNombreView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mNameView = findViewById(R.id.nameView);
        mCategoryView = findViewById(R.id.categoryView);
        mDateView = findViewById(R.id.dateView);
        mIdView = findViewById(R.id.idView);
        mNombreView = findViewById(R.id.nombreView);

        mNameView.setText("NOM");
        mCategoryView.setText("CATEGORY");
        mDateView.setText("DATE");
        mIdView.setText("ID");
        mNombreView.setText("NOMBRE");

        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.GET);
        httpCall.setUrl("http://192.168.1.12:8090/api/product/read.php");
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
                    Log.d("STATUS","*******************"+Jarray);

                    for (int i = 0; i < Jarray.length(); i++)
                    {
                        JSONObject Jasonobject = Jarray.getJSONObject(i);
                        Log.d("TEST","************"+Jasonobject);
                        Log.i("log_tag","id: "+Jasonobject.getInt("id"));
                        mNameView.setText(Jasonobject.getString("name"));
                        mIdView.setText(Jasonobject.getString("id"));
                        mCategoryView.setText(Jasonobject.getString("category_name"));
                        mDateView.setText(Jasonobject.getString("date"));
                        mNombreView.setText(Jasonobject.getString("nombre"));
                    }
                }catch(JSONException e){
                    Log.e("log_tag", "Error parsing data "+e.toString());
                }

                Log.d("STATUS","*******************"+response);
            }
        }.execute(httpCall);

 }
}