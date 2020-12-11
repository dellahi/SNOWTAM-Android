package com.example.snowtam.Asset;

import com.android.volley.RequestQueue;
import com.example.snowtam.Model.ListSnowtam;
import com.google.gson.Gson;

import java.util.ArrayList;

public class BasicAsset {
    public static ArrayList<String> listURLvalue = new ArrayList<>();
    public static RequestQueue requestQueue;
    public static Gson json = new Gson();
    public static ListSnowtam listsnowtam = new ListSnowtam();
    public static ArrayList<String> champAll = new ArrayList<>();
    public static ArrayList<String> decodefieldsArray = new ArrayList<>();
    public static ArrayList<String> decodeinfosArray = new ArrayList<>();
}
