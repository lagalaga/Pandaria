package br.com.pandaria.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

import br.com.pandaria.Entity.Ingrediente;

public class DaoProdutoIngrediente {

    private SQLiteDatabase db;

    public void inserir(long idProduto, long idIngrediente,float qtdUsada, SQLiteDatabase db) {
        this.db = db;

        ContentValues values = new ContentValues();

        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_INGREDIENTE, idIngrediente);
        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO, idProduto);
        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_QTD_USADA, qtdUsada);

        db.insert(ProdutoIngredienteContract.ProdutoIngrediente.NOME_TABELA, null, values);
    }
    public void deletar(long idProduto,SQLiteDatabase db){
        this.db = db;

        String selection = ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO + " = ? ";
        String[] selectionArgs = {Long.toString(idProduto)};

        db.delete(ProdutoIngredienteContract.ProdutoIngrediente.NOME_TABELA, selection, selectionArgs);
    }

    public void atualizar(long idProduto, long idIngrediente,float qtdUsada, SQLiteDatabase db) {

        this.db = db;

        ContentValues values = new ContentValues();
        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_INGREDIENTE, idIngrediente);
        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_QTD_USADA, qtdUsada);

        String selection = ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO + " = ? ";
        String[] selectionArgs = {Long.toString(idProduto)};

        db.update(IngredienteContract.Ingrediente.NOME_TABELA, values, selection, selectionArgs);



    }

    public HashMap<Ingrediente,Float> listarIngredientes(long idProduto,SQLiteDatabase db){

        this.db = db;
        HashMap<Ingrediente,Float> ingredientes = new HashMap<>();
        Ingrediente ingrediente;

        String selection = ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO;
        String[] args = {Long.toString(idProduto)};

        String[] colunas = {
                ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_INGREDIENTE,
                ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_QTD_USADA
        };

        Cursor c = db.query(
                ProdutoIngredienteContract.ProdutoIngrediente.NOME_TABELA,
                colunas,
                selection,
                args,
                null,
                null,
                null
        );

        c.moveToFirst();
        while(c.moveToNext()){

            ingrediente = new Ingrediente();
            ingrediente.setId(c.getLong(c.getColumnIndex(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_INGREDIENTE)));
            ingredientes.put(ingrediente,c.getFloat(c.getColumnIndex(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_QTD_USADA)));

        }
        c.close();

        return ingredientes;

    }

}
