package com.nanoxcorp.emasomorepaires;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlConnection extends SQLiteOpenHelper {
    public static final String TBL_NAME = "tble";
    public static final String TBL_FLASHCARD = "flash";
    public static final String TBL_PREFERENCES = "pref";
    public static final String FLASH_ID = "flash";
    public static final String COLOR = "color";
    public static final String QUESTION = "ques";
    public static final String ANSWER  = "ans";
    public static final String U_NAME = "user";
    public static final String P_WORD = "pass";
    public static final  String DB_NAME = "testdb";
    public SqlConnection(Context context,int version){
        super(context,SqlConnection.DB_NAME,null,version);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Query = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " ( " + U_NAME+ " TEXT, " + P_WORD + " TEXT )";
        String flashcardQuery = "CREATE TABLE IF NOT EXISTS " + TBL_FLASHCARD + " (" + FLASH_ID + ", " + QUESTION + ", " + ANSWER + ")";
        String preferenceQuery = "CREATE TABLE IF NOT EXISTS " + TBL_PREFERENCES + " (" + FLASH_ID + ", " + COLOR + ")";
        sqLiteDatabase.execSQL(Query);
        sqLiteDatabase.execSQL(flashcardQuery);
        sqLiteDatabase.execSQL(preferenceQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        String Query = "DROP TABLE IF EXISTS " + TBL_NAME;
        sqLiteDatabase.execSQL(Query);

    }


}
