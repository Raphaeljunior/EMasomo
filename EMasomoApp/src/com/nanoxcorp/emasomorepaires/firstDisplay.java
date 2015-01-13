package com.nanoxcorp.emasomorepaires;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class firstDisplay extends Activity {
    TabHost th;
    //UI requires more editing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        th.addTab(th.newTabSpec("1").setIndicator("eMasomo").setContent(R.id.tab1));
        th.addTab(th.newTabSpec("2").setIndicator("Past Papers").setContent(R.id.tab2));
        th.addTab(th.newTabSpec("3").setIndicator("Library").setContent(R.id.tab3));
        th.addTab(th.newTabSpec("4").setIndicator("Forum").setContent(R.id.tab4));
    }
}
