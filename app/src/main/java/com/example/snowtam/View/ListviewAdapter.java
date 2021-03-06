package com.example.snowtam.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.snowtam.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class ListviewAdapter extends ArrayAdapter {

    Activity context;
    ArrayList<String>  aInfos;
    ArrayList<String> aLettres;

    ListviewAdapter (Activity c, ArrayList<String>  aLettres, ArrayList<String>  aInfos) {
        super(c, R.layout.row, aLettres);
        this.context = c;
        this.aInfos=aInfos;
        this.aLettres=aLettres;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();;
        @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.row, parent, false);
        TextView lettre = row.findViewById(R.id.textView1);
        TextView info = row.findViewById(R.id.textView2);

        // now set our resources on views
        lettre.setText(aLettres.get(position));
        info.setText(aInfos.get(position));

        return row;
    }
}