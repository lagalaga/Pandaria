package br.com.pandaria.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.silvasoa2.pandaria.R;


import br.com.pandaria.Dao.PandariaDbHelper;
import br.com.pandaria.Entity.Ingrediente;
import br.com.pandaria.Entity.TipoDeIngrediente;
import br.com.pandaria.Business.b_Ingrediente;


public class InserirIngrediente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_ingrediente);

        final PandariaDbHelper dbHelper = new PandariaDbHelper(this);
        final Toast mensagem = new Toast(this);
        mensagem.setDuration(Toast.LENGTH_SHORT);

        final EditText txtNome = (EditText) findViewById(R.id.txtNomeIngre);
        final EditText txtPeso = (EditText) findViewById(R.id.txtPesoIngre);
        final Button btnIncluir = (Button) findViewById(R.id.btnIncluir);
        final Spinner tipoIngrediente = (Spinner) findViewById(R.id.spinner_tipo_ingrediente);
        String[] tipos = {
                TipoDeIngrediente.INGREDIENTE.toString(),
                TipoDeIngrediente.EMBALAGEM.toString()
                };

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
        tipoIngrediente.setAdapter(adapter);

        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setNome(txtNome.getText().toString().trim());
                ingrediente.setTipoDeIngrediente(tipoIngrediente.getSelectedItem().toString().trim());
                ingrediente.setQtdDoPacote(Float.parseFloat(txtPeso.getText().toString().trim()));

                if(new b_Ingrediente().b_Inserir(ingrediente,dbHelper)){
                    mensagem.setText("Ingrediente Cadastrado");
                    mensagem.show();
                }else{
                    mensagem.setText("Erro ao cadastrar");
                    mensagem.show();
                }



            }
        });



    }
}
