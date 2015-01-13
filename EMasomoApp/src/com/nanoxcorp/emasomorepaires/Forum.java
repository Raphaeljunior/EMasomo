package com.nanoxcorp.emasomorepaires;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class Forum extends Activity {
    TabHost th;
    ListView tab1,tab2,tab3;
    EditText q;
    ArrayList<String[]> resultCopy;
    ArrayList<String> finale;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);
        resultCopy = new ArrayList<String[]>();
        finale = new ArrayList<String>();
        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        tab1 = (ListView) findViewById(R.id.tab1);
        tab2 = (ListView) findViewById(R.id.tab2);
        tab3 = (ListView) findViewById(R.id.tab3);
        forumAdapter tab1Adapter = new forumAdapter(this,getData(""));//TODO
        forumAdapter tab2Adapter = new forumAdapter(this,getData(""));
        forumAdapter tab3Adapter = new forumAdapter(this,getData(""));
        tab1.setAdapter(tab1Adapter);
        tab2.setAdapter(tab2Adapter);
        tab3.setAdapter(tab3Adapter);
        tab1.setOnItemClickListener(listener);
        tab2.setOnItemClickListener(listener);
        tab3.setOnItemClickListener(listener);
        th.addTab(th.newTabSpec("trending").setIndicator("Trending").setContent(R.id.tab1));
        th.addTab(th.newTabSpec("solved").setIndicator("Solved").setContent(R.id.tab2));
        th.addTab(th.newTabSpec("new").setIndicator("New").setContent(R.id.tab3));

    }
    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            viewDialog(i);
        }
    };
    public ArrayList<String> getData(String url){
        GetData gd = new GetData(url);
        resultCopy = gd.retData();
        for(int i = 0;i<resultCopy.size();i++){
            String[] y = resultCopy.get(i);
            finale.add(y[0] + ":" + y[1] + ":" + y[2] + ":" + y[3] + ":" + y[4]);
        }
        return  finale;
    }
    public void viewDialog(int i){
        Dialog dialog = new Dialog(this);
        dialog.setTitle("More Info");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.more_info);
        WebView web = (WebView) findViewById(R.id.webView);
        web.clearHistory();
        web.setWebChromeClient(new WebChromeClient());
        web.getSettings().getBuiltInZoomControls();
        web.getSettings().setAllowContentAccess(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(finale.get(i).split(":")[2]);

    }

}
