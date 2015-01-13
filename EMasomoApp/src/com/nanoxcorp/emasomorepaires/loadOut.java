package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.util.*;

public class loadOut {
    Crud crud;
    Context context;
    public static ArrayList<FlashCard> flashCards;
    public static ArrayList<Books> books;
    ArrayList<Integer> Ids,bIds;
    SparseArray<HashMap<String, String>> qna;
    SparseArray<HashMap<String, String>> tnd;
    public static SparseIntArray prefs;


    public loadOut(Context context){
        flashCards = new ArrayList<FlashCard>();
        books = new ArrayList<Books>();
        Ids = new ArrayList<Integer>();
        bIds = new ArrayList<Integer>();
        prefs = new SparseIntArray();
        qna = new SparseArray<HashMap<String,String>>();
        tnd = new SparseArray<HashMap<String,String>>();
        //crud = new Crud(context,1);
        //qna = crud.getQnA();
        //tnd = crud.getTnD();
        //prefs = crud.getPreferences();

        //Test Space
        Ids.add(1);
        Ids.add(2);
        //Ids.add(3);
        bIds.add(1);
        bIds.add(2);
        //bIds.add(3);
        HashMap<String, String> c = new HashMap<String, String>();
        HashMap<String, String> d = new HashMap<String, String>();
        HashMap<String, String> e = new HashMap<String, String>();
        c.put("10","one");
        d.put("20","two");
        e.put("20","two0");
        qna.put(1,c);
        qna.put(2,c);
        qna.put(3,c);
        tnd.append(1,e);
        tnd.append(2,d);
        tnd.append(3,c);
        prefs.put(1,19);
        prefs.put(2,38);
        prefs.put(3,19);
        //Test Space

        for(int x = 0; x< Ids.size();x++){
            int id = Ids.get(x);
            flashCards.add(createInstance(id, prefs.get(id), qna.get(id)));
        }
        for(int i = 0;i<bIds.size();i++){
            int bid = bIds.get(i);
            books.add(createBookInstance(bid,tnd.get(bid)));
        }

    }
    private FlashCard createInstance(int id,int color,HashMap<String, String> qna){
        return new FlashCard(id,color,qna);
    }
    private Books createBookInstance(int id, HashMap<String, String> data){
        return new Books(id,data);
    }
}
