package br.com.pandaria.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.silvasoa2.pandaria.R;

import java.util.ArrayList;

import br.com.pandaria.Entity.Ingrediente;

/**
 * Created by SilvaSoA2 on 23/09/2016.
 */

public class AdapterListIngredienteProduto extends ArrayAdapter {

    private ArrayList<Ingrediente> ingredientes,ingredientesEscolhidos;
    private Context context;

    public AdapterListIngredienteProduto(Context context,int textViewResourceId,
                                         ArrayList<Ingrediente> ingredientes){
        super(context,textViewResourceId,ingredientes);
        this.context = context;
        this.ingredientes = new ArrayList<>();
        this.ingredientesEscolhidos = new ArrayList<>();
        ingredientes.addAll(ingredientes);

    }


    //Classe que define cada linha da lista.
    private class ViewHolder{
        TextView nome;
        CheckBox escolha;
        EditText qtdUsada;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder holder = null;

        if(convertView == null){

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ingre_tela_produto,null);

            holder = new ViewHolder();
            holder.nome = (TextView) convertView.findViewById(R.id.lbl_nome_ingre);
            holder.escolha = (CheckBox) convertView.findViewById(R.id.cbx_ingre_select);
            holder.qtdUsada = (EditText) convertView.findViewById(R.id.txtIngreQtdUsada);
            convertView.setTag(holder);

            holder.escolha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;

                    Ingrediente ingrediente = (Ingrediente) cb.getTag();
                    if(cb.isChecked()){
                        ingredientesEscolhidos.add(ingrediente);
                    }else{
                        ingredientesEscolhidos.remove(ingrediente);
                    }
                }
            });

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Ingrediente ingrediente = ingredientes.get(position);
        holder.nome.setText(ingrediente.getNome());
        holder.escolha.setTag(ingrediente);

        return convertView;

    }

}
