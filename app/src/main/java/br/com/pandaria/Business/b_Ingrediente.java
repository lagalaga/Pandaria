package br.com.pandaria.Business;

import android.database.sqlite.SQLiteOpenHelper;

import br.com.pandaria.Dao.DaoIngrediente;
import br.com.pandaria.Entity.Ingrediente;

public class b_Ingrediente {

    public boolean b_Inserir(Ingrediente ingrediente, SQLiteOpenHelper helper){
        return new DaoIngrediente().inserir(ingrediente,helper);
    }
}
