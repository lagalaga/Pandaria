package br.com.pandaria.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Selection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pandaria.Entity.Encomenda;
import br.com.pandaria.Entity.Venda;

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

        String selection = EncomendaContract.Encomenda.NOME_COLUNA_FK_VENDA + " = ?";
        String[] args = {Long.toString(encomenda.getId())};

        daoVenda.atualizar(encomenda,helper);


        if(db.update(EncomendaContract.Encomenda.NOME_TABELA,values,selection,args) > 0){
            db.close();
            return true;
        }else {
            return false;
        }

    }

    public List<Encomenda> listarEncomendas(SQLiteOpenHelper helper,Date dataInicial,Date dataFinal){

        this.db = helper.getReadableDatabase();
        List<Encomenda> encomendas = new ArrayList<>();
        Encomenda encomenda;

        SimpleDateFormat formatterParaClasse = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterParaBd = new SimpleDateFormat("yyyy-MM-dd");

        String inicio = formatterParaBd.format(dataInicial);
        String fim = formatterParaBd.format(dataFinal);

        // Join na tabela venda e encomenda
        String query = "SELECT * FROM " +
                VendaContract.Venda.NOME_TABELA + "JOIN" +
                EncomendaContract.Encomenda.NOME_TABELA +
                " ON " + VendaContract.Venda._ID + " = " +
                EncomendaContract.Encomenda.NOME_COLUNA_FK_VENDA +
                "WHERE " + VendaContract.Venda.NOME_COLUNA_DATA + " BETWEEN " + inicio + " AND " + fim ;

        Cursor c = db.rawQuery(query,null);

        c.moveToFirst();

        while(c.moveToNext()){

            encomenda = new Encomenda();
            encomenda.setId(c.getLong(c.getColumnIndex(VendaContract.Venda._ID)));

        }



    }

}
