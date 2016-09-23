package br.com.pandaria.View;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import br.com.pandaria.Entity.Ingrediente;
import br.com.pandaria.Entity.Produto;
import br.com.pandaria.Utility.AdapterListIngredienteProduto;

public class InserirProduto extends AppCompatActivity {

    AdapterListIngredienteProduto adapterIngreProd = null;

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

        //Popula a lista de ingredientes
        adapterIngreProd = new AdapterListIngredienteProduto(
                this,
                R.layout.ingre_tela_produto,
                (ArrayList<Ingrediente>) ingredientes);
        lstIngrediente.setAdapter(adapterIngreProd);

        //Popula a lista de embalagens
        adapterIngreProd = new AdapterListIngredienteProduto(
                this,
                R.layout.ingre_tela_produto,
                (ArrayList<Ingrediente>) embalagens);
        lstEmbalagens.setAdapter(adapterIngreProd);


        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto produto = new Produto();
                produto.setNome(txtNome.getText().toString());
                produto.setPrecoDeVenda(Float.parseFloat(txtPreco.getText().toString()));


            }
        });

    }
}
