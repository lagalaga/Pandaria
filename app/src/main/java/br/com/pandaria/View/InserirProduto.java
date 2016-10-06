package br.com.pandaria.View;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.silvasoa2.pandaria.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.pandaria.Business.b_Estoque;
import br.com.pandaria.Dao.DaoIngrediente;
import br.com.pandaria.Entity.Estoque;
import br.com.pandaria.Entity.Ingrediente;
import br.com.pandaria.Entity.Produto;
import br.com.pandaria.Utility.AdapterListIngredienteProduto;
import br.com.pandaria.Utility.DialogListIngredienteProduto;

public class InserirProduto extends AppCompatActivity implements DialogListIngredienteProduto.NoticeDialogListener{

    private b_Estoque bEstoque;
    private AdapterListIngredienteProduto adapterIngreProd = null;
    private HashMap<Ingrediente,Float> escolhidos;
    private List<Ingrediente> ingredientes;
    private float custo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_produto);

        //Instancia os membros
        escolhidos = new HashMap<>();
        bEstoque = new b_Estoque();

        //Pegar componentes da tela
        final EditText txtNome = (EditText) findViewById(R.id.txtNomeProduto);
        final EditText txtPreco = (EditText) findViewById(R.id.txtPrecoProduto);
        final Button btnIncluir = (Button) findViewById(R.id.btnIncluir);


        //Listar todos os ingredientes
         ingredientes = new DaoIngrediente(this).listarIngredientes(0);

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

        preencheLista(ingredientes);



        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto produto = new Produto();
                produto.setNome(txtNome.getText().toString());
                produto.setPrecoDeVenda(Float.parseFloat(txtPreco.getText().toString()));


            }
        });



    }

    @Override
    public void onDialogPositiveClick(Float qtdUsada, Ingrediente ingrediente) {

        escolhidos.put(ingrediente,qtdUsada);
        ingrediente.setEscolhido(true);

        //Pega todos as entradas do ingrediente
        List<Estoque> estoques = bEstoque.b_BuscarEstoquestPorIngrediente(ingrediente.getId(),this);

        //Calcula o Custo
        custo = custo + br.com.pandaria.Utility.Custo.custoPorIngrediente(estoques.get(0).getPrecoUnit(),ingrediente.getQtdDoPacote(),qtdUsada);

        //Mostra o custo atual
        final TextView lblCusto = (TextView) findViewById(R.id.lblCustoCalculado);
        lblCusto.setText(Float.toString(custo));

        //Atualiza lista
        preencheLista(ingredientes);



    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();

    }

    private void preencheLista(List<Ingrediente> ingredientes){

        final ListView lstIngrediente = (ListView) findViewById(R.id.lstIngrediente);

        //Popula a lista de ingredientes
        adapterIngreProd = new AdapterListIngredienteProduto(
                this,
                R.layout.ingre_tela_produto,
                (ArrayList<Ingrediente>) ingredientes);
        lstIngrediente.setAdapter(adapterIngreProd);


    }
}
