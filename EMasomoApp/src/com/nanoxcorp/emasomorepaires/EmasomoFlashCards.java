package com.nanoxcorp.emasomorepaires;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class EmasomoFlashCards extends Activity {
    TabHost th;
    ListView latest,trending;
    ArrayList<String[]> res;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emasomo_flashcards);
        res = new ArrayList<String[]>();
        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        latest = (ListView) findViewById(R.id.tab1);
        trending = (ListView) findViewById(R.id.tab2);
        th.setup();
        getData();
        setAdapters();
        addTabs();

    }
    public void addTabs(){
        th.addTab(th.newTabSpec("latest").setIndicator("Latest").setContent(R.id.tab1));
        th.addTab(th.newTabSpec("trending").setIndicator("Trending").setContent(R.id.tab2));
    }
    public void getData(){
        GetData gd = new GetData("");//TODO:URL
        res =  gd.retData();
    }
    public void setAdapters(){
        ArrayList<String> finale = new ArrayList<String>();
        for(int i = 0;i<res.size();i++){
            String[] y = res.get(i);
            finale.add(y[0] + ":" + y[1] + ":" + y[2] + ":" + y[3] + ":" + y[4]);
        }
        efcAdapter tab1 = new efcAdapter(getApplicationContext(),finale);
        efcAdapter tab2 = new efcAdapter(getApplicationContext(),finale);
        latest.setAdapter(tab1);
        trending.setAdapter(tab2);

    }
}
