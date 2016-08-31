package br.com.pandaria.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PandariaDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pandaria.db";

    public PandariaDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(IngredienteContract.SQL_CREATE_ENTRIES);

    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        System.out.println("not implemented yet");
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("not implemented yet");
    }

}
