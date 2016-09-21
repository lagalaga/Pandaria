package br.com.pandaria.View;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;

import com.example.silvasoa2.pandaria.R;

import java.util.ArrayList;
import java.util.List;

import br.com.pandaria.Dao.DaoIngrediente;
import br.com.pandaria.Dao.IngredienteContract;
import br.com.pandaria.Entity.Ingrediente;

public class InserirProduto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_produto);

        //Pegar componentes da tela
        final EditText txtNome = (EditText) findViewById(R.id.txtNomeProduto);
        final EditText txtPreco = (EditText) findViewById(R.id.txtPrecoProduto);
        final TextView lblCusto = (TextView) findViewById(R.id.lblCustoCalculado);
        final Button btnIncluir = (Button) findViewById(R.id.btnIncluir);
        final ListView lstEmbalagens = (ListView) findViewById(R.id.lstEmbalagemTelaProdutos);
        final ExpandableListView lstIngrediente = (ExpandableListView) findViewById(R.id.explstIngrediente);

        //Listar todos os ingredientes
        List<Ingrediente> ingredientes = new DaoIngrediente(this).listarIngredientes(0);

        //TODO testar as lists APAGAR ISSO DEPOIS
        Ingrediente ingreTeste;

        for(int i=0;i<10;i++){
            ingreTeste = new Ingrediente();
            ingreTeste.setNome("nome "+ i);
            if(i%2==0){
                ingreTeste.setTipoDeIngrediente("embalagem");
            }else{
                ingreTeste.setTipoDeIngrediente("ingrediente");
            }
            ingredientes.add(ingreTeste);
        }

        //Separa ingredientes entre embalagem e ingrediente
        List<Ingrediente> embalagens = new ArrayList<>();
        for(Ingrediente ingrediente : ingredientes){
            if(ingrediente.getTipoDeIngrediente().toString().equalsIgnoreCase("embalagem")){
                embalagens.add(ingrediente);
                ingredientes.remove(ingrediente);
            }
        }

        //Popula a lista de embalagens
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,embalagens);
        lstEmbalagens.setAdapter(adapter);

        //Popula a lista de ingredientes
        adapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,ingredientes);
        lstIngrediente.setAdapter(adapter);







    }
}
