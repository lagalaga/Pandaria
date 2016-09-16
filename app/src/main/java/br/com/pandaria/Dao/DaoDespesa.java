package br.com.pandaria.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pandaria.Entity.Despesa;

public class DaoDespesa extends PandariaDbHelper{


    private SQLiteDatabase db;

    public DaoDespesa(Context context){
        super(context);
    }

    public boolean inserir(Despesa despesa,SQLiteOpenHelper helper){

        this.db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DespesaContract.Despesa.NOME_COLUNA_DESCRICAO,despesa.getDescricao());
        values.put(DespesaContract.Despesa.NOME_COLUNA_VALOR,despesa.getValor());
        values.put(DespesaContract.Despesa.NOME_COLUNA_DATA,despesa.getData().toString());

        if(db.insert(DespesaContract.Despesa.NOME_TABELA,null,values) == -1){
            return false;
        }
        else{
            return true;
        }


    }

    public boolean deletar(int id, SQLiteOpenHelper helper){
        String selection = DespesaContract.Despesa._ID + " = ? ";
        String[] selectionArgs = {"id"};
        if(db.delete(DespesaContract.Despesa.NOME_TABELA,selection,selectionArgs) == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean atualizar(Despesa despesa, SQLiteOpenHelper helper){

        this.db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DespesaContract.Despesa.NOME_COLUNA_DESCRICAO,despesa.getDescricao());
        values.put(DespesaContract.Despesa.NOME_COLUNA_VALOR,despesa.getValor());
        values.put(DespesaContract.Despesa.NOME_COLUNA_DATA,despesa.getData().toString());

        String selection = DespesaContract.Despesa._ID + " = ? ";
        String[] selectionArgs = {"id"};

        if(db.update(DespesaContract.Despesa.NOME_TABELA, values,selection,selectionArgs) < 1){
            return false;
        }
        else{
            return true;
        }

    }

    public List<Despesa> listarDespesasPorData(SQLiteOpenHelper helper,Date dataInicial,Date dataFinal){

        this.db = helper.getReadableDatabase();
        List<Despesa> despesas = new ArrayList<>();
        Despesa despesa;
        SimpleDateFormat formatterParaClasse = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterParaBd = new SimpleDateFormat("yyyy-MM-dd");


        String inicio = formatterParaBd.format(dataFinal);
        String fim = formatterParaBd.format(dataFinal);

        String selection = DespesaContract.Despesa.NOME_COLUNA_DATA + " BETWEEN ? AND ? ";
        String[] selectionArgs = {inicio,fim};

        Cursor c = db.query(
                DespesaContract.Despesa.NOME_TABELA,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        c.moveToFirst();
        while(c.moveToNext()){

            despesa = new Despesa();
            despesa.setId(c.getInt(c.getColumnIndex(DespesaContract.Despesa._ID)));
            despesa.setDescricao(c.getString(c.getColumnIndex(DespesaContract.Despesa.NOME_COLUNA_DESCRICAO)));
            despesa.setValor(c.getFloat(c.getColumnIndex(DespesaContract.Despesa.NOME_COLUNA_VALOR)));

            try {
                despesa.setData(formatterParaClasse.parse(c.getString(c.getColumnIndex(DespesaContract.Despesa.NOME_COLUNA_DATA))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            despesas.add(despesa);
        }
        c.close();

        return despesas;

    }

}
