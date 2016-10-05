package br.com.pandaria.Business;

import android.content.Context;

import java.util.List;

import br.com.pandaria.Dao.DaoEstoque;
import br.com.pandaria.Dao.EstoqueContract;
import br.com.pandaria.Entity.Estoque;

/**
 * Created by SilvaSoA2 on 05/10/2016.
 */

public class b_Estoque {
    public List<Estoque> b_BuscarEstoquestPorIngrediente(long id, Context context){
        return  new DaoEstoque(context).buscarEstoquesPorIngrediente(id);
    }
}
