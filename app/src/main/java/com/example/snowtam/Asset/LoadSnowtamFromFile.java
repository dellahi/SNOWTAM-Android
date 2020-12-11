package com.example.snowtam.Asset;

import android.content.Context;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class LoadSnowtamFromFile {

    JSONArray arr ;

    public LoadSnowtamFromFile(Context context, String code ){
        String json = null ;
        String[] list = null;
        try {
            InputStream is = context.getAssets().open(code+".json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


            //JSONObject obj = new JSONObject(json);

            this.arr = new JSONArray( json );

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONArray getJsonArray() {
        return arr;
    }

}
