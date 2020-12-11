package com.example.snowtam.Model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.snowtam.Asset.BasicAsset.champAll;
import static com.example.snowtam.Asset.BasicAsset.json;
import static com.example.snowtam.Asset.BasicAsset.listURLvalue;
import static com.example.snowtam.Asset.BasicAsset.listsnowtam;
import static com.example.snowtam.Asset.BasicAsset.requestQueue;

public class SnowtamAdapter {

    public int ParsJsonFromICAO(String url, int index) {

        if (index < 5 && index > 0) {
            String Url = url+ listURLvalue.get(index-1);
            JsonArrayRequest objectRequest = new JsonArrayRequest(
                    Request.Method.GET, Url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Log.e("ResponseArray", response.toString());
                                ConvertJSonToJava(FindSnwoTam(response), json);
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
        if (index == 0) {
            return index;
        }
        return ParsJsonFromICAO(url, index-1);
    }
    public void ConvertJSonToJava(JSONObject response, Gson json) {
        Snowtam reslt = json.fromJson(String.valueOf(response),Snowtam.class);
        listsnowtam.liste.add(reslt);
        //Log.e("ResponseID",reslt.getId());
        champAll.add(reslt.all);
        Log.e("Champs all : ", champAll.toString());
    }

    public JSONObject FindSnwoTam ( JSONArray nowTamList) throws JSONException {

        JSONObject SnowtamjsonObject = (JSONObject) nowTamList.get(0);
        for (int i=0; i< nowTamList.length(); i++){
            SnowtamjsonObject = (JSONObject) nowTamList.get(i);
            if( SnowtamjsonObject.getString("all").contains("SNOWTAM") ) {
                /*Log.e("ResponseIF", "if");
                Log.e("ResponseSnow",SnowtamjsonObject.toString());*/
                // index = i;
                return SnowtamjsonObject;
            }
        }
        return SnowtamjsonObject ;
    }

    public void ParsJSONFromFile (JSONArray response) throws JSONException {
        ConvertJSonToJava(FindSnwoTam(response), json);
    }
}
