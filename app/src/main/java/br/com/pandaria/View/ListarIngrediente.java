package br.com.pandaria.View;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silvasoa2.pandaria.R;


import java.io.Serializable;
import java.util.List;

import br.com.pandaria.Dao.PandariaDbHelper;
import br.com.pandaria.Entity.Ingrediente;
import br.com.pandaria.Business.b_Ingrediente;
import br.com.pandaria.Entity.TipoDeIngrediente;

public class ListarIngrediente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ingrediente);

        List<Ingrediente> ingredientes;
        ingredientes = new b_Ingrediente().b_ListarIngredientes(0,this);
        final ListView listaIngre = (ListView) findViewById(R.id.list_ingredientes);


        if(ingredientes.isEmpty()){
            TextView txt = new TextView(this);
            txt.setText(R.string.string_lblListaIngreVazia);
            txt.setId(0);
            txt.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            RelativeLayout relative = (RelativeLayout) findViewById(R.id.view_list_ingredientes);
            relative.removeView(listaIngre);
            relative.addView(txt);



        }else{

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,ingredientes);
            listaIngre.setAdapter(adapter);
            listaIngre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Ingrediente ingredienteSelecionado = (Ingrediente) listaIngre.getItemAtPosition(position);
                    Intent intent = new Intent(ListarIngrediente.this,EditarIngrediente.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ingreSelect",(Serializable)ingredienteSelecionado);
                    intent.putExtra("b_ingre",bundle);
                    startActivity(intent);



                }
            });



        }

    }
}
