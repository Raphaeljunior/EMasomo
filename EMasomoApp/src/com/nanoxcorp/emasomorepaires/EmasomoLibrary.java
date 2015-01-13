package com.nanoxcorp.emasomorepaires;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class EmasomoLibrary extends Activity {
    TabHost th;
    GridView tab1,tab2,tab3;
    ArrayList<String[]> resultCopy;
    ArrayList<String> finale, sata;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emasomo_library);
        resultCopy = new ArrayList<String[]>();
        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        tab1 = (GridView) findViewById(R.id.tab1);
        tab2 = (GridView) findViewById(R.id.tab2);
        tab3 = (GridView) findViewById(R.id.tab3);
        //Test ArrayList
        ArrayList<String[]> d = new ArrayList<String[]>();
        d.add(new String[]{"book","www.iconarchive.com/download/i03462/rokey/the-last-order/bitmap-image.ico","www.iconarchive.com/download/i03462/rokey/the-last-order/bitmap-image.ico"
        ,"TITLE","AAAAAAABBBBBBB"});
        d.add(new String[]{"book22","www.iconarchive.com/download/i03462/rokey/the-last-order/bitmap-image.ico","www.iconarchive.com/download/i03462/rokey/the-last-order/bitmap-image.ico"
                ,"TITLE22","KKKKKKKKKKK"});
        sata = new ArrayList<String>();
        for(int c = 0 ;c<d.size();c++){
            String[] v = d.get(c);
            sata.add( v[0] + ":" + v[1] + ":" + v[2] + ":" + v[3] + ":"+ v[4]);
        }

        //
        universalAdapter tab1Adapter = new universalAdapter(this,sata);//TODO: Fill in
        universalAdapter tab2Adapter = new universalAdapter(this,sata);
        universalAdapter tab3Adapter = new universalAdapter(this,sata);
        tab1.setAdapter(tab1Adapter);
        tab2.setAdapter(tab2Adapter);
        tab3.setAdapter(tab3Adapter);
        tab1.setOnItemClickListener(listener);
        tab2.setOnItemClickListener(listener);
        tab3.setOnItemClickListener(listener);
        th.addTab(th.newTabSpec("featured").setIndicator("Feature").setContent(R.id.tab1));
        th.addTab(th.newTabSpec("humanities").setIndicator("Humanities").setContent(R.id.tab2));
        th.addTab(th.newTabSpec("sciences").setIndicator("Sciences").setContent(R.id.tab3));


    }
    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            switch(parent.getId()){
                case R.id.tab1:
                    alertDialog("Featured Download",position);
                case R.id.tab2:
                    alertDialog("Humanity Download",position);
                case R.id.tab3:
                    alertDialog("Sciences Download",position);
            }
        }
    };
    public ArrayList<String> getData(String url){
        GetData gd = new GetData(url);
        resultCopy = gd.retData();
        finale = new ArrayList<String>();
        for(int i = 0;i<resultCopy.size();i++){
            String[] y = resultCopy.get(i);
            finale.add(y[0] + ":" + y[1] + ":" + y[2] + ":" + y[3] + ":" + y[4]);
        }
        return finale;
    }
    public void alertDialog(String title, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(sata.get(position).split(":")[4]);
        builder.setCancelable(true);
        builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                initiateDownload(position);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    public void initiateDownload(int position) {
        downloadFacade dl = new downloadFacade(sata.get(position).split(":")[1], this);//
        if (dl.getSuccess()) {
            Toast.makeText(this, "Download complete", Toast.LENGTH_SHORT).show();
            MyLibrary.addBook(sata.get(position).split(":")[3], sata.get(position).split(":")[4]);
        }else{
            Toast.makeText(this, "Download Error", Toast.LENGTH_SHORT).show();

        }
    }

}
