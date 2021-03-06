package br.com.pandaria.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PandariaDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "pandaria.db";
    protected SQLiteDatabase db;

    public PandariaDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(IngredienteContract.SQL_CREATE_ENTRIES);
        db.execSQL(DespesaContract.SQL_CREATE_ENTRIES);
        db.execSQL(EncomendaContract.SQL_CREATE_ENTRIES);
        db.execSQL(EstoqueContract.SQL_CREATE_ENTRIES);
        db.execSQL(ItemContract.SQL_CREATE_ENTRIES);
        db.execSQL(ProdutoContract.SQL_CREATE_ENTRIES);
        db.execSQL(ProdutoIngredienteContract.SQL_CREATE_ENTRIES);
        db.execSQL(VendaContract.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL(IngredienteContract.SQL_DELETE_ENTRIES);
        db.execSQL(DespesaContract.SQL_DELETE_ENTRIES);
        db.execSQL(EncomendaContract.SQL_DELETE_ENTRIES);
        db.execSQL(EstoqueContract.SQL_DELETE_ENTRIES);
        db.execSQL(ItemContract.SQL_DELETE_ENTRIES);
        db.execSQL(ProdutoContract.SQL_DELETE_ENTRIES);
        db.execSQL(ProdutoIngredienteContract.SQL_DELETE_ENTRIES);
        db.execSQL(VendaContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("not implemented yet");
    }

}
