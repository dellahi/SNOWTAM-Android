package com.example.snowtam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.toolbox.Volley;
import com.example.snowtam.Model.ListSnowtam;
import com.example.snowtam.Model.Snowtam;
import com.example.snowtam.View.DisplayInfo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public EditText code1;
    public EditText code2;
    public EditText code3;
    public EditText code4;
    public Button send;
    ArrayList<String> listValue;
    public String URL;
    public RequestQueue requestQueue;
    public Gson json;
    public ListSnowtam listsnowtam = new ListSnowtam();
    public String api_key; // "7fb0e070-302a-11eb-8517-83ab2aba25d1"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        send = findViewById(R.id.btnsend);
        listValue =  new ArrayList<>();
        json = new Gson();
        requestQueue = Volley.newRequestQueue(this);

/*        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listValue.add(code1.getText().toString());
                listValue.add(code2.getText().toString());
                listValue.add(code3.getText().toString());
                listValue.add(code4.getText().toString());
                ParsJsonFromICAO();
        }
        });*/
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DisplayInfo.class));
            }
        });
}
    public void ParsJsonFromICAO() {

            for (int i=0; i< listValue.size() ; i++) {
                URL = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key="+api_key+"&format=json&criticality=1&locations="+ listValue.get(i);

                JsonArrayRequest objectRequest = new JsonArrayRequest(
                        Request.Method.GET, URL, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    Log.e("ResponseArray",response.toString());
                                    for (int i=0; i< response.length(); i++){
                                        JSONObject jsonObjectTest = (JSONObject) response.get(i);
                                        if( jsonObjectTest.getString("all").contains("SNOWTAM") ) {
                                            Log.e("ResponseIF", "if");
                                            Log.e("ResponseSnow",jsonObjectTest.toString());
                                            ConvertJSonToJava(jsonObjectTest, json);
                                            // index = i;
                                            break;
                                        }

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
            }
        }
    public void ConvertJSonToJava(JSONObject response, Gson json) {
        Snowtam reslt = json.fromJson(String.valueOf(response),Snowtam.class);
        listsnowtam.liste.add(reslt);
        Log.e("ResponseID",reslt.getId());
    }

}