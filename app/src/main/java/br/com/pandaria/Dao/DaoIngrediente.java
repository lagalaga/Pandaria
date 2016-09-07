package br.com.pandaria.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

import br.com.pandaria.Entity.Ingrediente;

public class DaoIngrediente {

    private SQLiteDatabase db;

    public boolean inserir(Ingrediente ingrediente, SQLiteOpenHelper helper){

        this.db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(IngredienteContract.Ingrediente.NOME_COLUNA_NOME,ingrediente.getNome());
        values.put(IngredienteContract.Ingrediente.NOME_COLUNA_TIPO,ingrediente.getTipoDeIngrediente().toString());
        values.put(IngredienteContract.Ingrediente.NOME_COLUNA_QTD,ingrediente.getQtdDoPacote());

        if(db.insert(IngredienteContract.Ingrediente.NOME_TABELA,null,values) == -1){

            return false;

        }
        else{
            return true;
        }
    }

    public boolean deletar(int id, SQLiteOpenHelper helper) {

        db = helper.getReadableDatabase();

        String selection = IngredienteContract.Ingrediente._ID + " = ? ";
        String[] selectionArgs = {Integer.toString(id)};

        if(db.delete(IngredienteContract.Ingrediente.NOME_TABELA, selection, selectionArgs) != 0){
            db.close();
            return true;
        }else {
            db.close();
            return false;
        }

    }

    public boolean atualizar(Ingrediente ingrediente, SQLiteOpenHelper helper) {

        this.db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(IngredienteContract.Ingrediente.NOME_COLUNA_NOME, ingrediente.getNome());
        values.put(IngredienteContract.Ingrediente.NOME_COLUNA_TIPO, ingrediente.getTipoDeIngrediente().toString());
        values.put(IngredienteContract.Ingrediente.NOME_COLUNA_QTD, ingrediente.getQtdDoPacote());

        String selection = IngredienteContract.Ingrediente._ID + " = ? ";
        String[] selectionArgs = {Integer.toString(ingrediente.getId())};

        if (db.update(IngredienteContract.Ingrediente.NOME_TABELA, values, selection, selectionArgs) > 0) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    public List<Ingrediente> listarIngredientes(SQLiteOpenHelper helper){

        this.db = helper.getReadableDatabase();
        List<Ingrediente> ingredientes = new ArrayList<>();
        Ingrediente ingrediente;

        Cursor c = db.query(
                DespesaContract.Despesa.NOME_TABELA,
                null,
                null,
                null,
                null,
                null,
                null
        );

        c.moveToFirst();
        while(c.moveToNext()){

            ingrediente = new Ingrediente();
            ingrediente.setId(c.getInt(c.getColumnIndex(IngredienteContract.Ingrediente._ID)));
            ingrediente.setNome(c.getString(c.getColumnIndex(IngredienteContract.Ingrediente.NOME_COLUNA_NOME)));
            ingrediente.setQtdDoPacote(c.getFloat(c.getColumnIndex(IngredienteContract.Ingrediente.NOME_COLUNA_QTD)));
            ingrediente.setTipoDeIngrediente(c.getString(c.getColumnIndex(IngredienteContract.Ingrediente.NOME_COLUNA_TIPO)));

            ingredientes.add(ingrediente);
        }
        c.close();

        return ingredientes;

    }


}
