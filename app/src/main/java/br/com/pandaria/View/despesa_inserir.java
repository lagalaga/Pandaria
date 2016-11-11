package br.com.pandaria.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.silvasoa2.pandaria.R;

import java.util.Calendar;
import java.util.Date;

import br.com.pandaria.Entity.Despesa;

public class despesa_inserir extends Fragment {

    private ButtonClickListener mListener;
    private Despesa despesa;

    public despesa_inserir() {
        // Required empty public constructor
    }


    public static despesa_inserir newInstance() {
        return new despesa_inserir();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_despesa_inserir, container, false);

        final EditText txtDesc = (EditText) view.findViewById(R.id.txtDespesaDescricao);
        final EditText txtValor = (EditText) view.findViewById(R.id.txtValorDespesa);
        final Button btnIncluir = (Button) view.findViewById(R.id.btnIncluirDespesa);

        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO:Checar por nulos
                despesa = new Despesa();
                despesa.setDescricao(txtDesc.getText().toString());
                despesa.setValor(Float.parseFloat(txtValor.getText().toString()));

                //TODO:Corrigir captura da data
                // despesa.setData(new Date().getTime());

                mListener.onButtonClick(despesa);


            }
        });


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ButtonClickListener) {
            mListener = (ButtonClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ButtonClickListener {
        // TODO: Update argument type and name
        void onButtonClick(Despesa despesa);
    }
}
