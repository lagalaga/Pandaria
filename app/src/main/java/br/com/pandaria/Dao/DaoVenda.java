package br.com.pandaria.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pandaria.Entity.Item;
import br.com.pandaria.Entity.Venda;

public class DaoVenda {

    private SQLiteDatabase db;
    private DaoItem daoItem = new DaoItem();

    public long inserir(Venda venda, SQLiteOpenHelper helper){

        this.db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VendaContract.Venda.NOME_COLUNA_DATA,venda.getDateVenda().toString());

        long idVenda = db.insert(VendaContract.Venda.NOME_TABELA,null,values);


        for (Item item : venda.getItems()) {
                daoItem.inserir(idVenda, item, db);
            }

        db.close();

       return idVenda;

    }

    // Talvez eu use esse método
    public boolean deletar(long idVenda){
        return false;
    }

    public boolean atualizar(Venda venda, SQLiteOpenHelper helper){

        this.db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(VendaContract.Venda.NOME_COLUNA_DATA,venda.getDateVenda().toString());

        String selection = VendaContract.Venda._ID + " = ?";
        String[] args = {Long.toString(venda.getId())};

        if(db.update(VendaContract.Venda.NOME_TABELA,values,selection,args) > 0){

            for(Item item : venda.getItems()){
                daoItem.atualizar(item.getId(),item,db);
            }
            db.close();
            return true;

        }else{
            return false;
        }

    }

    public List<Venda> listarVendas(Date dataInicial,Date dataFinal,SQLiteOpenHelper helper){

        this.db = helper.getReadableDatabase();
        Venda venda;
        List<Venda> vendas = new ArrayList<>();
        List<Item> itens;
        DaoItem daoItem = new DaoItem();

        SimpleDateFormat formatterParaClasse = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterParaBd = new SimpleDateFormat("yyyy-MM-dd");



        String selection = VendaContract.Venda.NOME_COLUNA_DATA + " BETWEEN ? AND ? ";
        String[] args = { formatterParaBd.format(dataInicial),formatterParaBd.format(dataFinal)};

        Cursor c = db.query(
                VendaContract.Venda.NOME_TABELA,
                null,
                selection,
                args,
                null,
                null,
                null
        );

        c.moveToFirst();

        while(c.moveToNext()){
            venda = new Venda();
            venda.setId(c.getLong(c.getColumnIndex(VendaContract.Venda._ID)));

            try {
                venda.setDateVenda(
                formatterParaClasse.parse(
                        c.getString(
                                c.getColumnIndex(
                                        VendaContract.Venda.NOME_COLUNA_DATA
                                )
                        )
                ));
            }catch (ParseException pex){
                pex.printStackTrace();

            }

            venda.setItems(daoItem.listarItem(venda.getId(),db));

        }

        return vendas;

    }

}
