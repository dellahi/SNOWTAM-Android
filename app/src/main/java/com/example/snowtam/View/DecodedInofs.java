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

public class DecodedInofs extends AppCompatActivity {
    ListView listView2;
    ImageView home;
    ImageView carte;
    Button coder;
    String infos[] = {"UUEEDecode","11151547Decode","UUEEDecoder","11151547Decoder","UUEEDecoder","11151547Decoder"};
    String lettres[] = {"A","B","A","B","A","B"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoded_inofs);
        listView2 = findViewById(R.id.listView2);
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DecodedInofs.this, MainActivity.class));
            }
        });
        coder = findViewById(R.id.btndecode);
        coder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DecodedInofs.this,DisplayInfo.class));
            }
        });
        carte = findViewById(R.id.maps);
        carte
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DecodedInofs.this, MapsActivity.class));
                    }
                });
        ListviewAdapter adapter = new ListviewAdapter(this, lettres, infos);
        listView2.setAdapter(adapter);
    }
}
