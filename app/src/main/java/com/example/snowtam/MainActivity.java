package com.example.snowtam;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.snowtam.Asset.BasicAsset.champAll;
import static com.example.snowtam.Asset.BasicAsset.listURLvalue;
import static com.example.snowtam.Asset.BasicAsset.requestQueue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.toolbox.Volley;
import com.example.snowtam.Asset.LoadSnowtamFromFile;
import com.example.snowtam.Model.SnowtamAdapter;
import com.example.snowtam.View.DisplayInfo;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    public EditText code1;
    public EditText code2;
    public EditText code3;
    public EditText code4;
    public Button send;
    Context c = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String api_key = "4d9fb4c0-3b1b-11eb-906e-773d4e58062b"; //""61512e40-3553-11eb-9586-b7ca39afa526";  "7fb0e070-302a-11eb-8517-83ab2aba25d1"
        final String URL = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key="+api_key+"&format=json&criticality=1&locations=";

        final SnowtamAdapter snowtamAdapter = new SnowtamAdapter();

        requestQueue = Volley.newRequestQueue(this);

        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        send = findViewById(R.id.btnsend);

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                airportCode();
              // int flag = snowtamAdapter.ParsJsonFromICAO(URL,5); appel de l'api
                LoadSnowtamFromFile reslt = new LoadSnowtamFromFile(c, listURLvalue.get(0));
                try {
                    snowtamAdapter.ParsJSONFromFile(reslt.getJsonArray());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(MainActivity.this, DisplayInfo.class));
            }
        });
    }

    public void airportCode() {
        listURLvalue.add(code1.getText().toString());
        listURLvalue.add(code2.getText().toString());
        listURLvalue.add(code3.getText().toString());
        listURLvalue.add(code4.getText().toString());
        listURLvalue.add("ENGM");
    }

}


/* Comment
  send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DisplayInfo.class));
            }
        });

    RequestFuture<JSONArray> future = RequestFuture.newFuture();
                JsonArrayRequest request = new JsonArrayRequest(URL, future, future);
                requestQueue.add(request);
                try {
                    JSONArray response = future.get(); //120, TimeUnit.SECONDS
                    Log.e("ResponseArray", response.toString());
                    ConvertJSonToJava(FindSnwoTam(response), json);
                    // this will block
                    }
                catch (InterruptedException e) { // exception handling
                        }
                catch (ExecutionException e) { // exception handling
                     } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("ResponseArray", "Future post");
 */