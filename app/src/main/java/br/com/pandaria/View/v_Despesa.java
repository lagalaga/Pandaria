package br.com.pandaria.View;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.silvasoa2.pandaria.R;

import java.util.List;

import br.com.pandaria.Business.b_Despesa;
import br.com.pandaria.Entity.Despesa;

public class v_Despesa extends AppCompatActivity implements despesa_inserir.ButtonClickListener{

    private b_Despesa bDespesa;
    private final Toast mensagem = Toast.makeText(this,null,Toast.LENGTH_SHORT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        bDespesa = new b_Despesa();

        //TODO: Ajeitar data
        //List<Despesa> despesas = bDespesa.b_Listar();

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_despesa,new despesa_inserir(),"despesaInserir")
                    .commit();
        }

    }

    @Override
    public void onButtonClick(Despesa despesa) {

        if(bDespesa.b_Incluir(despesa,this) == true){
            mensagem.setText("Despesa incluida");
            mensagem.show();
        }
        else{
            mensagem.setText("Erro ao incluir despesa");
            mensagem.show();
        }

    }

    private void atualizarDespesas(){

    }
}
