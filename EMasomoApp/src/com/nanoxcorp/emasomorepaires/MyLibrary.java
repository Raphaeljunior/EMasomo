package com.nanoxcorp.emasomorepaires;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Random;

public class MyLibrary extends Activity {
    TabHost th;
    GridView tab1,tab2,tab3;
    private static String quest,ans;
    EditText question,answer;
    Dialog dialog;
    private static int seekVal;
    private static Crud dbConn;
    int flashId;
    private static libraryAdapter tab2Adapter;
    private static bookAdapter tab1Adapter;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_library);
        //TEST
        loadOut load = new loadOut(this);
        //TEST
        dbConn = new Crud(this,1);
        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        tab1 = (GridView) findViewById(R.id.tab1);
        tab2 = (GridView) findViewById(R.id.tab2);
        tab3 = (GridView) findViewById(R.id.tab3);
        tab1Adapter = new bookAdapter(this,loadOut.books);
        tab2Adapter = new libraryAdapter(this,null);
        libraryAdapter tab3Adapter = new libraryAdapter(this,null);
        tab1.setAdapter(tab1Adapter);
        tab2.setAdapter(tab2Adapter);
        //tab3.setAdapter(tab3Adapter);
        //normal touch
        tab1.setOnItemClickListener(listener);
        tab2.setOnItemClickListener(listener);
        //tab3.setOnItemClickListener(listener);
        //long click
        tab1.setOnItemLongClickListener(blListener);
        tab2.setOnItemLongClickListener(lListener);

        th.addTab(th.newTabSpec("books").setIndicator("Books").setContent(R.id.tab1));
        th.addTab(th.newTabSpec("flashcards").setIndicator("Flashcards").setContent(R.id.tab2));
        th.addTab(th.newTabSpec("papers").setIndicator("Papers").setContent(R.id.tab3));


    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent,View view, int position, long id){
            switch (view.getId()){
                case R.id.tab1:
                    Books book = (Books)parent.getItemAtPosition(position);
                    assert book!= null;
                    openBook(book.getId());
                case R.id.tab2:
                    FlashCard flash = (FlashCard)parent.getItemAtPosition(position);
                    assert flash != null;
                    flashId = flash.getId();
                    optionsDialog(flashId);
                case R.id.tab3:

                case R.id.relM:

            }

        }
    };

    AdapterView.OnItemLongClickListener lListener = new AdapterView.OnItemLongClickListener(){
        @Override
        public boolean onItemLongClick(AdapterView<?> parent,View view, int position, long id) {//only for Flashcards
            FlashCard flash = (FlashCard)parent.getItemAtPosition(position);
            assert flash != null;
            optionsDialog(flash.getId());
            return false;
        }
    };
    AdapterView.OnItemLongClickListener blListener = new AdapterView.OnItemLongClickListener(){
        @Override
        public boolean onItemLongClick(AdapterView<?> parent,View view, int position, long id) {//only for Flashcards
            Books book = (Books)parent.getItemAtPosition(position);
            assert book != null;
           viewInfo(book.getId());
            return false;
        }
    };
    public void optionsDialog(int flashId){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_a);
        dialog.setTitle("Preference Settings");
        question = (EditText) dialog.findViewById(R.id.editText);
        answer = (EditText) dialog.findViewById(R.id.editText2);
        TextView instruction = (TextView) dialog.findViewById(R.id.textView);
        SeekBar sBar = (SeekBar) dialog.findViewById(R.id.seekBar);
        Button ok = (Button) dialog.findViewById(R.id.accept);
        ok.setOnClickListener(buttonListener);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(buttonListener);
        sBar.setOnSeekBarChangeListener(seekListener);
        dialog.setCancelable(true);
        dialog.show();

    }
    public void viewInfo(int bookId){
        final Dialog bdialog = new Dialog(this);
        bdialog.setContentView(R.layout.b_dialog);
        TextView txt = (TextView) bdialog.findViewById(R.id.textView);
        Button bttn = (Button) bdialog.findViewById(R.id.button);
        for(int i = 0; i< loadOut.books.size();i++) {
            if(bookId == loadOut.books.get(i).getId()){
                txt.setText(loadOut.books.get(i).getDescription());
                dialog.setTitle(loadOut.books.get(i).getTitle());
            }
        }
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdialog.cancel();
            }
        });
        bdialog.setCancelable(true);
        bdialog.show();
    }
    //Still testing feature
    SeekBar.OnSeekBarChangeListener seekListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            View currentView = dialog.findViewById(R.id.relM);
            seekVal = i;
            //currentView.setBackgroundColor(Color.BLUE);

        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };
    //

    View.OnClickListener buttonListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.accept:
                    quest = question.getText().toString();
                    ans = answer.getText().toString();
                    save();
                case R.id.cancel:
                    dialog.cancel();
            }
        }
    };
    public boolean prefSet(){//TODO: check if pref's have been set
        return false;
    }
    public ArrayList<String> getData(){//TODO: get from SD
        return null;
    }
    public void save(){
        for(int i = 0;i<loadOut.flashCards.size();i++){
            if(flashId == loadOut.flashCards.get(i).getId()){
                loadOut.flashCards.get(i).setColorVal(seekVal);
                loadOut.flashCards.get(i).setQuestion(quest);
                loadOut.flashCards.get(i).setAnswer(ans);
                tab2Adapter.notifyDataSetChanged();
            }
        }

    }
    private static int generateId(){
        Random rand = new Random();
        int randomInt = 0;
        for(int i =0;i<10;i++){
            randomInt = rand.nextInt();
        }
        return randomInt;
    }
    public static void createFlash(){
        final int id = generateId();
        dbConn.addFlash(id);
        loadOut.flashCards.add(new FlashCard(id));
        tab2Adapter.notifyDataSetChanged();
        Thread addToDb = new Thread(){
          @Override
        public void run(){
              super.run();
              dbConn.addQuestion(id,quest,ans);
              dbConn.addPreferences(seekVal,id);
          }
        };
        addToDb.start();
    }
    public static void addBook(final String title, final String description){
        final int id = generateId();
        loadOut.books.add(new Books(id,title,description));
        //Notify data set changed
        Thread addToDb = new Thread(){
            @Override
            public void run() {
                super.run();
                dbConn.addBook(id,title,description);
            }
        };
        addToDb.start();
    }
    public void openBook(int bookId){
        //TODO:
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mylib,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.flash:
                createFlash();
                return true;
            case R.id.book:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

