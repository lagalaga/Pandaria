package br.com.pandaria.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.silvasoa2.pandaria.R;

public class Despesa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_despesa,new despesa_inserir(),"despesaInserir")
                    .commit();
        }

    }
}
