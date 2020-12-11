package com.example.snowtam.View;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.snowtam.Asset.BasicAsset.champAll;
import static com.example.snowtam.Asset.BasicAsset.decodefieldsArray;
import static com.example.snowtam.Asset.BasicAsset.decodeinfosArray;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.snowtam.MainActivity;
import com.example.snowtam.MapsActivity;
import com.example.snowtam.R;

import java.util.ArrayList;


public class DisplayInfo extends AppCompatActivity {
    ListView listView;
    ImageView home;
    ImageView carte;
    Button decoder;
    public ArrayList<String> fieldsArray = new ArrayList<>();
    public ArrayList<String> infosArray = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        listView = findViewById(R.id.listView);

        loadAndDecodeJsonData(champAll.get(0));

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayInfo.this, MainActivity.class));
            }
        });
        decoder = findViewById(R.id.btncode);
        carte = findViewById(R.id.maps);
        carte
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DisplayInfo.this, MapsActivity.class));
                    }
                });

        ListviewAdapter adapter = new ListviewAdapter(this, fieldsArray, infosArray);
        listView.setAdapter(adapter);

        decoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayInfo.this, DecodedInofs.class));
            }
        });

    }
    private void loadAndDecodeJsonData(String all ){

        String titre = "nom Aeroport";
        CharSequence notams = "SNOWTAM";
        if((all.contains(notams))){
            int index = 0;
            index = all.indexOf("A)");

            final String newLine = "\n";
            all = all.substring( index );
            int k =1 ;
            int c = 1;
            Log.e("testSubstring", all);
            for(String line : all.split(newLine)){

                Log.e("test","for" + line);
                if(line.contains( "A)" )) {
                    int indB = 0;
                    if (line.contains("B)")) {
                        indB = line.indexOf("B)");
                        // On ajoute les champs de "A"
                        infosArray.add(line.substring(2, (int) indB));
                        fieldsArray.add(line.substring(0, 2));
                        decodeinfosArray.add(line.substring(2));
                        decodefieldsArray.add(line.substring(0, 2));
                        decodeinfosArray.add(line.substring(2, (int) indB) + "-" + titre);
                        // On ajoute les champs de "B"
                        infosArray.add(line.substring((int) indB + 2, (int) line.length()));
                        fieldsArray.add("B)");
                        String ChampsB = line.substring((int) indB + 2, (int) line.length());
                        String jour = "";
                        String mois = "";
                        String Heure = "";
                        String minute = "";

                        jour = ChampsB.substring(2, 4);
                        mois = ChampsB.substring(0, 2);
                        Heure = ChampsB.substring(4, 6);
                        minute = ChampsB.substring(6, 8);


                        switch (mois) {
                            case "01":
                                mois = "janvier";
                                break;
                            case "02":
                                mois = "février";
                                break;
                            case "03":
                                mois = "mars";
                                break;
                            case "04":
                                mois = "avril";
                                break;
                            case "05":
                                mois = "mai";
                                break;
                            case "06":
                                mois = "juin";
                                break;
                            case "07":
                                mois = "juillet";
                                break;
                            case "08":
                                mois = "août";
                                break;
                            case "09":
                                mois = "septembre";
                                break;
                            case "10":
                                mois = "octobre";
                                break;
                            case "11":
                                mois = "novembre";
                                break;
                            case "12":
                                mois = "décembre";
                                break;
                            default:
                                mois = "mois";
                        }

                        decodeinfosArray.add(jour + " " + mois + " " + Heure + "h" + minute + " UTC");
                        decodefieldsArray.add("B");
                    }

                   else {
                       // On ajoute les champs de "A"
                    infosArray.add(line.substring(2));
                    fieldsArray.add(line.substring(0, 2));

                    decodeinfosArray.add(line.substring(2));
                    decodefieldsArray.add(line.substring(0, 2));
                }
                }

                else if (line.contains("B)") && c == 1){
                    c=0;
                    int indC = 0;
                    indC = line.indexOf("C)");
                    int indB = line.indexOf("B)");
                    // On ajoute les champs de "B"
                    infosArray.add(line.substring((int)indB, (int)indC));
                    fieldsArray.add("B)");

                    String ChampsB = line.substring((int)indB, (int)indC);
                    String jour= "";
                    String mois = "";
                    String Heure = "";
                    String minute = "";

                    jour = ChampsB.substring( 2,4 );
                    mois = ChampsB.substring( 0,2 );
                    Heure = ChampsB.substring( 4,6 );
                    minute = ChampsB.substring( 6,8 );


                    switch(mois) {
                        case "01":
                            mois="janvier";
                            break;
                        case "02":
                            mois="février";
                            break;
                        case "03":
                            mois="mars";
                            break;
                        case "04":
                            mois="avril";
                            break;
                        case "05":
                            mois="mai";
                            break;
                        case "06":
                            mois="juin";
                            break;
                        case "07":
                            mois="juillet";
                            break;
                        case "08":
                            mois="août";
                            break;
                        case "09":
                            mois="septembre";
                            break;
                        case "10":
                            mois="octobre";
                            break;
                        case "11":
                            mois="novembre";
                            break;
                        case "12":
                            mois="décembre";
                            break;
                        default:
                            mois="mois";
                    }

                    decodeinfosArray.add( jour+" "+mois+" "+Heure+"h"+minute+" UTC" );
                    decodefieldsArray.add("B");

                    String subC  = line.substring( (int)indC, (int)line.length());

                    System.out.println( "SubC ==> " + subC );

                    if(subC.contains("F)")){
                       int indF = subC.indexOf("F)");
                      // On ajoute les champs de "C"
                        infosArray.add(subC.substring(2, (int)indF));
                        fieldsArray.add(subC.substring(0,2));
                        decodeinfosArray.add("RUNWAY"+subC.substring(2, (int)indF));
                        decodefieldsArray.add("C)");
                        
                    }
                    else{// On ajoute les champs de "C"
                    infosArray.add(line.substring((int)indC+2, (int)line.length()));
                    fieldsArray.add("C)");

                    decodeinfosArray.add("RUNWAY"+line.substring((int)indC+2, (int)line.length()));
                    decodefieldsArray.add("C");
                    }

                }
                else if(line.contains( "F)" ) &&  k == 1){
                    k=0;
                    int indF = 0;
                    indF = line.indexOf("F)");

                    String subF  = line.substring( (int)indF, (int)line.length());

                    System.out.println( "SubF ==> " + subF );

                    // On ajoute les champs de "C"
                    /*infosArray.add(line.substring(2, (int)indF));
                    fieldsArray.add(line.substring(0,2));

                    decodeinfosArray.add("RUNWAY"+line.substring(2, (int)indF));
                    decodefieldsArray.add("C");*/
                    if(subF.contains( "G)" )){

                        int indG = 0;
                        indG = subF.indexOf("G)");

                        // On ajoute les champs de "F"
                        infosArray.add(subF.substring(2, (int)indG));
                        fieldsArray.add(subF.substring(0,2));

                        // On ajoute les champs de "G"
                        infosArray.add(subF.substring((int)indG + 2 , (int)subF.length()));
                        fieldsArray.add(subF.substring(0,2));
                    }
                    else if(subF.contains( "H)" )){

                        int indH = 0;
                        indH = subF.indexOf("H)");

                        // On ajoute les champs de "F"
                        infosArray.add(subF.substring(2, (int)indH));
                        fieldsArray.add(subF.substring(0,2));

                        // On ajoute les champs de "H"
                        infosArray.add(subF.substring((int)indH + 2 , (int)subF.length()));
                        fieldsArray.add(subF.substring(0,2));
                    }

                    else {
                        infosArray.add(subF.substring((int)indF + 2, (int)subF.length()));
                        fieldsArray.add(subF.substring(0 ,2));
                    }

                }

                else {
                    infosArray.add(line.substring(2, (int)line.length()));
                    fieldsArray.add(line.substring(0,2));
                }

            }
        }
    }
}
