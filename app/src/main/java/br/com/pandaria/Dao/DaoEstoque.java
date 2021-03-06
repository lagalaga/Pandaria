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

import br.com.pandaria.Entity.Estoque;
import br.com.pandaria.Entity.Ingrediente;

public class DaoEstoque extends PandariaDbHelper {

    private Context context;
    public DaoEstoque(Context context){
        super(context);
        this.context = context;
    }

    public boolean inserir(Estoque estoque){

        this.db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EstoqueContract.Estoque.NOME_COLUNA_DATA_ENTRADA,estoque.getData().toString());
        values.put(EstoqueContract.Estoque.NOME_COLUNA_PRECO_UNIT,estoque.getPrecoUnit());
        values.put(EstoqueContract.Estoque.NOME_COLUNA_QTD,estoque.getQtdDePacotes());
        values.put(EstoqueContract.Estoque.NOME_COLUNA_FK_INGREDIENTE,estoque.getIngrediente().getId());

        if(db.insert(EstoqueContract.Estoque.NOME_TABELA,null,values) != -1){
            db.close();
            return true;
        }else {
            db.close();
            return false;
        }

    }

    public boolean deletar(long id){

        String selection = EstoqueContract.Estoque._ID + " = ?";
        String[] args = {Long.toString(id)};

        if(db.delete(EstoqueContract.Estoque.NOME_TABELA,selection,args)!=0){
            db.close();
            return true;

        }else{
            db.close();
            return false;
        }
    }


    //Lista todos dos registros por intervalo de data
    public List<Estoque> listarEstoque(Date dataInicial, Date dataFinal) {

        this.db = this.getReadableDatabase();
        List<Estoque> estoques = new ArrayList<>();
        Estoque estoque;
        List<Ingrediente> ingredientes;
        DaoIngrediente daoIngrediente = new DaoIngrediente(context);

        SimpleDateFormat formatterParaClasse = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterParaBd = new SimpleDateFormat("yyyy-MM-dd");

        String inicio = formatterParaBd.format(dataInicial);
        String fim = formatterParaBd.format(dataFinal);

        String selection = EstoqueContract.Estoque.NOME_COLUNA_DATA_ENTRADA + " BETWEEN ? AND ?";
        String[] args = {inicio,fim};

        Cursor c = db.query(
                EstoqueContract.Estoque.NOME_TABELA,
                null,
                selection,
                args,
                null,
                null,
                null
        );

        c.moveToFirst();
        while(c.moveToNext()){

            estoque = new Estoque();
            estoque.setId(c.getLong(c.getColumnIndex(EstoqueContract.Estoque._ID)));
            estoque.setPrecoUnit(c.getFloat(c.getColumnIndex(EstoqueContract.Estoque.NOME_COLUNA_PRECO_UNIT)));
            estoque.setQtdDePacotes(c.getFloat(c.getColumnIndex(EstoqueContract.Estoque.NOME_COLUNA_QTD)));

            try{
            estoque.setData(

                    formatterParaClasse.parse(
                            c.getString(
                                    c.getColumnIndex(
                                            EstoqueContract.Estoque.NOME_COLUNA_DATA_ENTRADA))));
            }catch (ParseException pex){
                pex.printStackTrace();
            }
            estoque.getIngrediente().setId(c.getLong(c.getColumnIndex(EstoqueContract.Estoque.NOME_COLUNA_FK_INGREDIENTE)));

            //Busca o ingrediente
            ingredientes = daoIngrediente.listarIngredientes(estoque.getIngrediente().getId());

            estoque.getIngrediente().setNome(ingredientes.get(0).getNome());
            estoque.getIngrediente().setQtdDoPacote(ingredientes.get(0).getQtdDoPacote());
            estoque.getIngrediente().setTipoDeIngrediente(ingredientes.get(0).getTipoDeIngrediente().toString());

            estoques.add(estoque);


        }
        c.close();
        return estoques;
    }

    public List<Estoque> buscarEstoquesPorIngrediente(long id){

        this.db = getReadableDatabase();
        List<Estoque> estoques = new ArrayList<>();
        Estoque estoque;
        String selection = EstoqueContract.Estoque.NOME_COLUNA_FK_INGREDIENTE + " = ?";
        String[] args = {Long.toString(id)};
        String order = EstoqueContract.Estoque.NOME_COLUNA_DATA_ENTRADA + " DESC";

        Cursor c = db.query(
                EstoqueContract.Estoque.NOME_TABELA,
                null,
                selection,
                args,
                null,
                null,
                order
        );

        c.moveToFirst();
        while (c.moveToNext()){

            estoque = new Estoque();
            estoque.setId(c.getLong(c.getColumnIndex(EstoqueContract.Estoque._ID)));
            estoque.setPrecoUnit(c.getFloat(c.getColumnIndex(EstoqueContract.Estoque.NOME_COLUNA_PRECO_UNIT)));
            estoque.setQtdDePacotes(c.getFloat(c.getColumnIndex(EstoqueContract.Estoque.NOME_COLUNA_QTD)));

            SimpleDateFormat formatterParaClasse = new SimpleDateFormat("dd/MM/yyyy");

            try{
                estoque.setData(

                        formatterParaClasse.parse(
                                c.getString(
                                        c.getColumnIndex(
                                                EstoqueContract.Estoque.NOME_COLUNA_DATA_ENTRADA))));
            }catch (ParseException pex){
                pex.printStackTrace();
            }

            estoques.add(estoque);


        }
        c.close();
        return estoques;
    }

}
