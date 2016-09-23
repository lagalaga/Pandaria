package br.com.pandaria.Business;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pandaria.Dao.DaoIngrediente;
import br.com.pandaria.Dao.DaoProduto;
import br.com.pandaria.Dao.DaoProdutoIngrediente;
import br.com.pandaria.Entity.Ingrediente;
import br.com.pandaria.Entity.Produto;

public class b_Ingrediente {

    public boolean b_Inserir(Ingrediente ingrediente, Context context){
        return new DaoIngrediente(context).inserir(ingrediente);
    }

    public List<Ingrediente> b_ListarIngredientes(long id,Context context) {
        return new DaoIngrediente(context).listarIngredientes(id);
    }

    public boolean b_Atualizar(Ingrediente ingredienteEditado, Context context) {
        return new DaoIngrediente(context).atualizar(ingredienteEditado);
    }

    public boolean b_Deletar(long id,Context context) {

        // Checa se não existem produtos com o ingrediente deletado
        List<Produto> produtos, produtosComIngredientes;
        List<Produto> listaProduto = new ArrayList<>();

        produtos = new DaoProdutoIngrediente(context).listarProdutosComIngrediente(id);
        if(!produtos.isEmpty()){
            for(Produto produto: produtos){
                produtosComIngredientes = new DaoProduto(context).listarProdutos(produto.getId());
                produto = produtosComIngredientes.get(0);
                listaProduto.add(produto);
            }
            Toast t = Toast.makeText(context,"Existem produtos com este ingrediente",Toast.LENGTH_SHORT);
            return false;
        } else {

            return new DaoIngrediente(context).deletar(id);
        }

    }
}
