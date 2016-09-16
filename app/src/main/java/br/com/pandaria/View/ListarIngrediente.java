package br.com.pandaria.View;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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


import java.util.List;

import br.com.pandaria.Dao.PandariaDbHelper;
import br.com.pandaria.Entity.Ingrediente;
import br.com.pandaria.Business.b_Ingrediente;

public class ListarIngrediente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ingrediente);

        final PandariaDbHelper dbHelper = new PandariaDbHelper(this);
        List<Ingrediente> ingredientes;
        ingredientes = new b_Ingrediente().b_ListarIngredientes(0,dbHelper);
        final ListView listaIngre = (ListView) findViewById(R.id.list_ingredientes);


        if(ingredientes.isEmpty()){
            TextView txt = new TextView(this);
            txt.setText(R.string.string_lblListaIngreVazia);
            txt.setId(0);
            txt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            RelativeLayout relative = (RelativeLayout) findViewById(R.id.view_list_ingredientes);
            relative.removeView(listaIngre);
            relative.addView(txt);



        }else{

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,ingredientes);
            listaIngre.setAdapter(adapter);
            final Toast teste = Toast.makeText(this,"teste",Toast.LENGTH_SHORT);
            listaIngre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Ingrediente ingredienteSelecionado = (Ingrediente) listaIngre.getItemAtPosition(position);
                    teste.setText(ingredienteSelecionado.getNome());
                    teste.show();
                }
            });



        }

    }
}