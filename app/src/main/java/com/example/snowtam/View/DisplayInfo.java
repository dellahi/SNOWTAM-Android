package com.example.snowtam.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.snowtam.MainActivity;
import com.example.snowtam.MapsActivity;
import com.example.snowtam.R;


public class DisplayInfo extends AppCompatActivity {
    ListView listView;
    ImageView home;
    ImageView carte;
    Button decoder;
    String infos[] = {"UUEE","11151547","UUEE","11151547","UUEE","11151547"};
    String lettres[] = {"A","B","A","B","A","B"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        listView = findViewById(R.id.listView);
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayInfo.this, MainActivity.class));
            }
        });
        decoder = findViewById(R.id.btncode);
        decoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayInfo.this,DecodedInofs.class));
            }
        });
        carte = findViewById(R.id.maps);
        carte
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DisplayInfo.this, MapsActivity.class));
                    }
                });
        ListviewAdapter adapter = new ListviewAdapter(this, lettres, infos);
        listView.setAdapter(adapter);
        /*ArrayList<String> infos = new ArrayList<>();
        ArrayList<String> lettres = new ArrayList<>();
        infos.set(0,"UUEE");
        lettres.set(0,"A");
        infos.set(1,"11151547");
        lettres.set(1,"B");*/

    }
}
