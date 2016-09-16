package br.com.pandaria.Business;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.List;

import br.com.pandaria.Dao.DaoIngrediente;
import br.com.pandaria.Entity.Ingrediente;

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
        return new DaoIngrediente(context).deletar(id);
    }
}
