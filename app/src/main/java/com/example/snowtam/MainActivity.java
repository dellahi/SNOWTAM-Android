package com.example.snowtam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.snowtam.Model.ListSnowtam;
import com.example.snowtam.Model.Snowtam;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListSnowtam listSnowtam = new ListSnowtam();
    public EditText code1;
    public EditText code2;
    public EditText code3;
    public EditText code4;
    public Button send;
    ArrayList<String> listValue;
    public String URL;
    public RequestQueue requestQueue;
    public Gson json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        send = findViewById(R.id.button);
        listValue =  new ArrayList<>();

        final Gson json = new Gson();
        requestQueue = Volley.newRequestQueue(this);

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String value1 =code1.getText().toString();
                listValue.add(value1);
                String value2 =code2.getText().toString();
                listValue.add(value2);
                String value3=code3.getText().toString();
                listValue.add(value3);
                String value4=code4.getText().toString();
                listValue.add(value4);
        for (int i=0; i<4; i++) {
            URL = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=7fb0e070-302a-11eb-8517-83ab2aba25d1&format=json&criticality=1&locations="+ listValue.get(i);
                Log.e("code1URL : ", URL);
            ApiCall(URL);
            }
            }
        });
}
    public void ConvertJSonToJava(JSONArray response, Gson json) {
        Type listSnowtamType = new TypeToken<ArrayList<Snowtam>>(){}.getType();
        listSnowtam.liste = json.fromJson(String.valueOf(response),listSnowtamType);
        Log.e("Response",listSnowtam.liste.get(0).getId());
    }
    public void ApiCall(String url ) {
        JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.e("Response",response.get(0).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ConvertJSonToJava(response, json);
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
        // Code here executes on main thread after user presses button
    }
}