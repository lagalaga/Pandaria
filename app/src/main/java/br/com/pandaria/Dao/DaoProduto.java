package br.com.pandaria.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.pandaria.Entity.Ingrediente;
import br.com.pandaria.Entity.Produto;

public class DaoProduto {

    private SQLiteDatabase db;
    private DaoProdutoIngrediente dpi = new DaoProdutoIngrediente();

    public boolean inserir(Produto produto, SQLiteOpenHelper helper){

        this.db = helper.getWritableDatabase();

        long idProduto;

        ContentValues values = new ContentValues();
        values.put(ProdutoContract.Produto.NOME_COLUNA_NOME,produto.getNome());
        values.put(ProdutoContract.Produto.NOME_COLUNA_VALOR_DE_VENDA,produto.getPrecoDeVenda());
        values.put(ProdutoContract.Produto.NOME_COLUNA_IS_INGREDIENTE,(produto.isIngrediente()?1:0));


        try{
            idProduto = db.insert(IngredienteContract.Ingrediente.NOME_TABELA,null,values);

            for(Map.Entry<Ingrediente,Float> ingrediente : produto.getIngredientes().entrySet()){
                dpi.inserir(idProduto,ingrediente.getKey().getId(),ingrediente.getValue(),db);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
      return true;

    }

    public boolean deletar(long id, SQLiteOpenHelper helper) {

        db = helper.getReadableDatabase();

        String selection = ProdutoContract.Produto._ID + " = ? ";
        String[] selectionArgs = {Long.toString(id)};

        try{
            db.delete(ProdutoContract.Produto.NOME_TABELA, selection, selectionArgs);
            dpi.deletar(id,db);

        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }

        return true;

    }

    public boolean atualizar(Produto produto, SQLiteOpenHelper helper) {

        this.db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProdutoContract.Produto.NOME_COLUNA_NOME,produto.getNome());
        values.put(ProdutoContract.Produto.NOME_COLUNA_VALOR_DE_VENDA,produto.getPrecoDeVenda());
        values.put(ProdutoContract.Produto.NOME_COLUNA_IS_INGREDIENTE,(produto.isIngrediente()?1:0));

        String selection = ProdutoContract.Produto._ID + " = ? ";
        String[] selectionArgs = {Long.toString(produto.getId())};

        try{
            db.update(ProdutoContract.Produto.NOME_TABELA, values, selection, selectionArgs);
            for(Map.Entry<Ingrediente,Float> ingrediente : produto.getIngredientes().entrySet()){
                dpi.inserir(produto.getId(),ingrediente.getKey().getId(),ingrediente.getValue(),db);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }

        return true;

    }

    public List<Produto> listarIngredientes(long idProd,SQLiteOpenHelper helper) {

        this.db = helper.getReadableDatabase();
        List<Produto> produtos = new ArrayList<>();
        Produto produto;
        String selection;
        String[] args = {};

        Cursor c, i;

        if (idProd == 0) {
            c = db.query(
                    ProdutoContract.Produto.NOME_TABELA,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            c.moveToFirst();
            while (c.moveToNext()) {

                produto = new Produto();
                produto.setId(c.getLong(c.getColumnIndex(ProdutoContract.Produto._ID)));
                produto.setNome(c.getString(c.getColumnIndex(ProdutoContract.Produto.NOME_COLUNA_NOME)));
                produto.setPrecoDeVenda(c.getFloat(c.getColumnIndex(ProdutoContract.Produto.NOME_COLUNA_VALOR_DE_VENDA)));
                produto.setIngrediente(c.getInt(c.getColumnIndex(ProdutoContract.Produto.NOME_COLUNA_IS_INGREDIENTE)));

                produto.setIngredientes(dpi.listarIngredientes(idProd, db));

                selection = IngredienteContract.Ingrediente._ID + " = ?";
                for (Map.Entry<Ingrediente, Float> ingrediente : produto.getIngredientes().entrySet()) {

                    args[0] = Long.toString(ingrediente.getKey().getId());
                    i = db.query(
                            IngredienteContract.Ingrediente.NOME_TABELA,
                            null,
                            selection,
                            args,
                            null,
                            null,
                            null
                    );

                    i.moveToFirst();
                    while (i.moveToNext()) {


                    }

                }


                produtos.add(produto);
            }
            c.close();
        } else {

        }

        return produtos;

    }
}
