package br.com.pandaria.Business;

import android.content.Context;

import java.util.Date;
import java.util.List;

import br.com.pandaria.Dao.DaoDespesa;
import br.com.pandaria.Entity.Despesa;

public class b_Despesa {

    public boolean b_Incluir(Despesa despesa, Context context){
        return new DaoDespesa(context).inserir(despesa);
    }

    public List<Despesa> b_Listar(Date dataInicial, Date dataFinal,Context context){
        return  new DaoDespesa(context).listarDespesasPorData(dataInicial,dataFinal);
    }
}
