package com.nanoxcorp.emasomorepaires;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends Activity {
    GridView grid;
    welcomeAdapter adapter;
    ArrayList<String> data;
    String item;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_a);
        data = new ArrayList<String>();
        setResources();
        grid = (GridView) findViewById(R.id.grid);
        adapter = new welcomeAdapter(this,data);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(listener);
    }
    public void setResources(){
        int lib = R.drawable.library;
        int vid = R.drawable.videos;
        int flash = R.drawable.flashcards;
        int forum = R.drawable.forum;
        int store = R.drawable.store;
        data.add(lib + ":My Library");
        data.add(vid + ":Videos");
        data.add(flash + ":Flash Cards");
        data.add(forum + ":Forum");
        data.add(store + ":Store");
    }
    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
           item = parent.getItemAtPosition(position).toString().split(":")[1];
            Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT).show();
            if(item.equals("My Library")){
                StartActivity('m');
            }else if(item.equals("Store")){
                StartActivity('s');
            }else if(item.equals("Flash Cards")){
                StartActivity('f');
            }

        }
    };
    public void StartActivity(char tag){
        switch (tag){
            case 'm'://My library
                startActivity(new Intent(this,MyLibrary.class));
            case 'v'://Videos

            case 'f'://Flash Cards
                startActivity(new Intent(this,EmasomoFlashCards.class));
            case 'o'://forum

            case 's'://store
                startActivity(new Intent(this, EmasomoLibrary.class));
        }
    }
}
