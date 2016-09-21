package br.com.pandaria.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.pandaria.Entity.Ingrediente;
import br.com.pandaria.Entity.Produto;

public class DaoProdutoIngrediente extends PandariaDbHelper {

    public DaoProdutoIngrediente(Context context){
        super(context);
    }

    public void inserir(long idProduto, long idIngrediente,float qtdUsada) {
        this.db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_INGREDIENTE, idIngrediente);
        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO, idProduto);
        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_QTD_USADA, qtdUsada);

        db.insert(ProdutoIngredienteContract.ProdutoIngrediente.NOME_TABELA, null, values);
    }
    public void deletar(long idProduto){
        this.db = getReadableDatabase();

        String selection = ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO + " = ? ";
        String[] selectionArgs = {Long.toString(idProduto)};

        db.delete(ProdutoIngredienteContract.ProdutoIngrediente.NOME_TABELA, selection, selectionArgs);
    }

    public void atualizar(long idProduto, long idIngrediente,float qtdUsada) {

        this.db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_INGREDIENTE, idIngrediente);
        values.put(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_QTD_USADA, qtdUsada);

        String selection = ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO + " = ? ";
        String[] selectionArgs = {Long.toString(idProduto)};

        db.update(IngredienteContract.Ingrediente.NOME_TABELA, values, selection, selectionArgs);



    }

    public HashMap<Ingrediente,Float> listarIngredientes(long idProduto) {

        this.db = this.getReadableDatabase();
        HashMap<Ingrediente, Float> ingredientes = new HashMap<>();
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
        while (c.moveToNext()) {

            ingrediente = new Ingrediente();
            ingrediente.setId(c.getLong(c.getColumnIndex(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_INGREDIENTE)));
            ingredientes.put(ingrediente, c.getFloat(c.getColumnIndex(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_QTD_USADA)));

        }
        c.close();

        return ingredientes;

    }

        public List<Produto> listarProdutosComIngrediente(long idIngre){

            List<Produto> produtos = new ArrayList<>();

            this.db = this.getReadableDatabase();
            Produto produto;

            String selection = ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_INGREDIENTE;
            String[] args = {Long.toString(idIngre)};

            String[] colunas = {
                    ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO,

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

                produto = new Produto();
                produto.setId(c.getLong(c.getColumnIndex(ProdutoIngredienteContract.ProdutoIngrediente.NOME_COLUNA_FK_PRODUTO)));
                produtos.add(produto);

            }
            c.close();

            return produtos;

        }

    }


