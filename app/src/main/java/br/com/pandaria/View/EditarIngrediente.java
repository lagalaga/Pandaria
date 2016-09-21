package br.com.pandaria.View;

import android.content.Context;
import android.content.Intent;
import android.icu.text.RelativeDateTimeFormatter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.silvasoa2.pandaria.R;

import br.com.pandaria.Business.b_Ingrediente;
import br.com.pandaria.Entity.Ingrediente;
import br.com.pandaria.Entity.TipoDeIngrediente;

public class EditarIngrediente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_ingrediente);

        //Pega ingrediente da outra Activity
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("ingreSelect");
        final Ingrediente ingrediente = (Ingrediente) bundle.get("b_ingre");


        //Pega os elementos da activity
        final EditText txtNome = (EditText) findViewById(R.id.txtNomeIngre);
        final EditText txtPeso = (EditText) findViewById(R.id.txtPesoIngre);
        final Spinner spnTipo = (Spinner) findViewById(R.id.spinner_tipo_ingrediente);
        Button btnEditar = (Button) findViewById(R.id.btnIncluir);
        final Toast mensagem = Toast.makeText(this,null,Toast.LENGTH_SHORT);
        final Context context = this;

        //Inclui o botão Excluir e altera o Incluir para Editar
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.relative_botao);
        btnEditar.setText(R.string.string_btnEditarIngre);
        Button btnExcluir = new Button(this);
        btnExcluir.setText(R.string.string_btnExcluirIngre);
        btnExcluir.setId(0);
        btnExcluir.setLayoutParams(new ViewGroup.LayoutParams(
                btnEditar.getHeight(),
                btnEditar.getWidth()
        ));
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(layout.getHeight(),layout.getWidth());
        rl.addRule(RelativeLayout.ALIGN_LEFT,btnEditar.getId());
        layout.addView(btnExcluir,rl);


        //Popula o spinner
        String[] tipos = {
                TipoDeIngrediente.INGREDIENTE.toString(),
                TipoDeIngrediente.EMBALAGEM.toString()
        };
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipos);
        spnTipo.setAdapter(adapter);

        //Preenche os campos com os dados do ingrediente
        txtNome.setText(ingrediente.getNome());
        txtPeso.setText(Float.toString(ingrediente.getQtdDoPacote()));
        spnTipo.setSelection(adapter.getPosition(ingrediente.getTipoDeIngrediente().toString()));

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ingrediente ingredienteEditado = new Ingrediente();
                ingredienteEditado.setNome(txtNome.getText().toString().trim());
                ingredienteEditado.setTipoDeIngrediente(spnTipo.getSelectedItem().toString().trim());
                ingredienteEditado.setQtdDoPacote(Float.parseFloat(txtPeso.getText().toString().trim()));
                ingredienteEditado.setId(ingrediente.getId());

                if(new b_Ingrediente().b_Atualizar(ingredienteEditado,context)){
                    mensagem.setText("Ingrediente Alterado");
                    mensagem.show();
                }else{
                    mensagem.setText("Erro ao cadastrar");
                    mensagem.show();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new b_Ingrediente().b_Deletar(ingrediente.getId(),context)){
                    mensagem.setText("Ingrediente Excluido");
                    mensagem.show();
                }
                    mensagem.setText("Erro ao cadastrar");
                    mensagem.show();
            }
        });


    }
}
