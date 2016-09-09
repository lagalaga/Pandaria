package br.com.pandaria.Dao;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.pandaria.Entity.Item;
import br.com.pandaria.Entity.Produto;
import br.com.pandaria.Entity.Venda;

public class DaoItem {

    private SQLiteDatabase db;

    public void inserir(long idVenda,Item item, SQLiteDatabase db){
        this.db = db;

        ContentValues values = new ContentValues();
        values.put(ItemContract.Item.NOME_COLUNA_QTD,item.getQtd());
        values.put(ItemContract.Item.NOME_COLUNA_FK_VENDA,idVenda);
        values.put(ItemContract.Item.NOME_COLUNA_FK_PRODUTO,item.getProduto().getId());

        db.insert(ItemContract.Item.NOME_TABELA,null,values);


    }

    public List<Item> listarItem(long idVenda, SQLiteDatabase db){

        Item item;
        List<Item> itens = new ArrayList<>();

        String selection = ItemContract.Item.NOME_COLUNA_FK_PRODUTO + " = ?";
        String[] args = {Long.toString(idVenda)};

        Cursor c = db.query(
                ItemContract.Item.NOME_TABELA,
                null,
                selection,
                args,
                null,
                null,
                null

        );
        c.moveToFirst();

        while(c.moveToNext()){
            item = new Item();
            item.setProduto(new Produto());

            item.setIdVenda(idVenda);
            item.setId(c.getLong(c.getColumnIndex(ItemContract.Item._ID)));
            item.setQtd(c.getLong(c.getColumnIndex(ItemContract.Item.NOME_COLUNA_QTD)));
            item.getProduto().setId(
                    c.getLong(
                            c.getColumnIndex(
                                    ItemContract.Item.NOME_COLUNA_FK_PRODUTO
                            )
                    )
            );

            itens.add(item);

        }

        return itens;

    }

    public void atualizar(long idVenda,Item item,SQLiteDatabase db){

        this.db = db;

        ContentValues values = new ContentValues();
        values.put(ItemContract.Item.NOME_COLUNA_QTD,item.getQtd());
        values.put(ItemContract.Item.NOME_COLUNA_FK_PRODUTO,item.getProduto().getId());

        String selection = ItemContract.Item.NOME_COLUNA_FK_VENDA + " = ? AND " +
                ItemContract.Item._ID + " = ?";
        String[] args = {Long.toString(idVenda),Long.toString(item.getId())};

        db.update(ItemContract.Item.NOME_TABELA,values,selection,args);

    }
}
