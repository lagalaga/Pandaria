package br.com.pandaria.Dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.pandaria.Entity.Encomenda;

public class DaoEncomenda {

    private SQLiteDatabase db;
    private DaoVenda daoVenda = new DaoVenda();

    public boolean inserir(Encomenda encomenda, SQLiteOpenHelper helper){

        this.db = helper.getWritableDatabase();

        long idVenda = daoVenda.inserir(encomenda,helper);

        ContentValues values = new ContentValues();
        values.put(EncomendaContract.Encomenda.NOME_COLUNA_CLIENTE,encomenda.getCliente());
        values.put(EncomendaContract.Encomenda.NOME_COLUNA_DESCRICAO,encomenda.getDescricao());
        values.put(EncomendaContract.Encomenda.NOME_COLUNA_VALOR,encomenda.getValor());
        values.put(EncomendaContract.Encomenda.NOME_COLUNA_IS_CANCELADA,encomenda.isCancelada());
        values.put(EncomendaContract.Encomenda.NOME_COLUNA_FK_VENDA,idVenda);

       if(db.insert(EncomendaContract.Encomenda.NOME_TABELA,null,values) != -1){
           db.close();
           return true;
       }else{
           db.close();
           return false;
       }

    }


    public boolean cancelar(long id,SQLiteOpenHelper helper){

        this.db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(EncomendaContract.Encomenda.NOME_COLUNA_IS_CANCELADA,false);

        String selection = EncomendaContract.Encomenda.NOME_COLUNA_FK_VENDA + " = ?";
        String[] args = {Long.toString(id)};

        if(db.update(EncomendaContract.Encomenda.NOME_TABELA,values,selection,args) > 0){
            db.close();
            return true;
        }else{
            db.close();
            return false;
        }

    }

    public boolean atualizar(Encomenda encomenda,SQLiteOpenHelper helper){

        this.db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(EncomendaContract.Encomenda.NOME_COLUNA_CLIENTE,encomenda.getCliente());
        values.put(EncomendaContract.Encomenda.NOME_COLUNA_DESCRICAO,encomenda.getDescricao());
        values.put(EncomendaContract.Encomenda.NOME_COLUNA_VALOR,encomenda.getValor());

        return true;

    }

}
