package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashMap;

public class Crud {
    SqlConnection connection;
    public Crud(Context context,int version){
        connection = new SqlConnection(context,version);
    }
    public void addUser(String u_name,String p_word){
        SQLiteDatabase db = connection.getWritableDatabase();
        String Query = "INSERT INTO " + SqlConnection.TBL_NAME + "(" + SqlConnection.U_NAME + ", " + SqlConnection.P_WORD+")" + " VALUES ('" +u_name + "', '" + p_word +"')" ;
        assert db != null;
        db.rawQuery(Query,null);
        db.close();
    }
    public void addFlash(int id){
        SQLiteDatabase db = connection.getWritableDatabase();
        String Query = "INSERT INTO " + SqlConnection.TBL_FLASHCARD + "(" + SqlConnection.FLASH_ID + ") VALUES ('" +id + "')" ;
        assert db != null;
        db.rawQuery(Query,null);
        db.close();
    }
    public void addQuestion(int id,String q,String a){
        SQLiteDatabase db = connection.getWritableDatabase();
        String Query = "INSERT INTO " + SqlConnection.TBL_FLASHCARD + "(" + SqlConnection.FLASH_ID + ", " + SqlConnection.QUESTION + ", " + SqlConnection.ANSWER + ")"+
                "VALUES ('" + id + "', '" + q + "', '" + a + "')";
        assert db != null;
        db.rawQuery(Query,null);
        db.close();
    }
    public void  addPreferences(int color,int flash){
        SQLiteDatabase db = connection.getWritableDatabase();
        String Query = "UPDATE " + SqlConnection.TBL_PREFERENCES + " SET " + SqlConnection.COLOR + " = '" + color + "' WHERE " + SqlConnection.FLASH_ID + " = '" + flash + "'";
        assert db != null;
        db.rawQuery(Query,null);
        db.close();
    }
    public void editFlash(int id,String q,String a){
        SQLiteDatabase db = connection.getWritableDatabase();
        String Query = "UPDATE " + SqlConnection.TBL_FLASHCARD + " SET " + SqlConnection.QUESTION + " = '" + q + "', " + SqlConnection.ANSWER + " = '" + a + "' WHERE "
                + SqlConnection.FLASH_ID + " = '" + id + "'";
        assert db != null;
        db.rawQuery(Query,null);
        db.close();
    }
    public void editPreference(int flash,int color){
        SQLiteDatabase db = connection.getWritableDatabase();
        String Query = "UPDATE " + SqlConnection.TBL_PREFERENCES + " SET " + SqlConnection.COLOR + " = '" + color + "' WHERE " + SqlConnection.FLASH_ID + " = '" + flash + "'";
        assert db != null;
        db.rawQuery(Query,null);
        db.close();
    }
    public void deleteFlash(){
        SQLiteDatabase db = connection.getWritableDatabase();
        String Query = "";
        assert db != null;
        db.rawQuery(Query,null);
        db.close();
    }
    private void deletePreference(){//called by delete flash
        SQLiteDatabase db = connection.getWritableDatabase();
        String Query = "";
        assert db != null;
        db.rawQuery(Query,null);
        db.close();
    }
    public boolean getUser(String u_name, String p_word){
        SQLiteDatabase db = connection.getWritableDatabase();
        String Query = "SELECT * FROM " + SqlConnection.TBL_NAME + " WHERE " + u_name + "= " + SqlConnection.U_NAME + " AND " + p_word + " = " + SqlConnection.P_WORD;
        Cursor c = db != null ? db.rawQuery(Query, null) : null;
        assert c != null;
        int user = c.getCount();
        db.close();
        return user == 1;
    }
    public ArrayList<Integer> getFlashIds(){
        SQLiteDatabase db = connection.getWritableDatabase();
        ArrayList<Integer> Ids = new ArrayList<Integer>();
        String Query = "";
        assert db != null;
        Cursor c = db.rawQuery(Query,null);
        assert c!=null;
        while(c.moveToNext()){
            Ids.add(c.getInt(0));
        }
        db.close();
        return Ids;
    }
    public SparseArray<HashMap<String, String>> getQnA(){
        SQLiteDatabase db = connection.getWritableDatabase();
        SparseArray<HashMap<String, String>> results = new SparseArray<HashMap<String, String>>();
        HashMap<String, String> data = new HashMap<String, String>();
        String Query = "";
        assert db != null;
        Cursor c = db.rawQuery(Query,null);
        assert c!=null;
        int i = 0;
        String ans = "";
        while(c.moveToNext()){
            data.put(c.getString(1),c.getString(2));
            results.put(Integer.parseInt(c.getString(0)),data);
            i++;
        }
        db.close();
        return results;
    }
    public SparseIntArray getPreferences(){//Sparse array instead of hash maps to improve performance
        SQLiteDatabase db = connection.getWritableDatabase();
        SparseIntArray res = new SparseIntArray();
        String Query = "";
        assert db != null;
        Cursor c = db.rawQuery(Query,null);
        assert c!=null;
        int i = 0;
        int  id = 0;
        while(c.moveToNext()){
            res.append(c.getInt(0),c.getInt(1));
            i++;
        }
        db.close();
        return res;
    }

    public void addBook(int id, String title, String description){
        SQLiteDatabase db = connection.getWritableDatabase();
        SparseIntArray res = new SparseIntArray();
        String Query = "";
        assert db != null;
        db.rawQuery(Query,null);

    }

    public SparseArray<HashMap<String, String>> getTnD(){
        SQLiteDatabase db = connection.getWritableDatabase();
        SparseArray<HashMap<String, String>> res = new SparseArray<HashMap<String, String>>();
        HashMap<String, String> data = new HashMap<String, String>();
        String Query = "";
        assert db != null;
        Cursor c = db.rawQuery(Query,null);
        assert c!=null;
        while(c.moveToNext()){
            data.put(c.getString(1),c.getString(2));
            res.put(c.getInt(0),data);
        }
        return res;
    }
    public ArrayList<Integer> getBookIds(){
        SQLiteDatabase db = connection.getWritableDatabase();
        ArrayList<Integer> res = new ArrayList<Integer>();
        String Query = "";
        assert db != null;
        Cursor c = db.rawQuery(Query,null);
        assert c!=null;
        while(c.moveToNext()){
            res.add(c.getInt(0));
        }
        return res;
    }

}
