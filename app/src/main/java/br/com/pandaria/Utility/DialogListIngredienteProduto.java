package br.com.pandaria.Utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.silvasoa2.pandaria.R;

import br.com.pandaria.Entity.Ingrediente;

public class DialogListIngredienteProduto extends DialogFragment{


    // Passa referencia do ingrediente selecionado para o Dialog
    public static DialogListIngredienteProduto newInstance(Ingrediente ingrediente){
        DialogListIngredienteProduto frag  = new DialogListIngredienteProduto();
        Bundle args = new Bundle();
        args.putSerializable("ingrediente", ingrediente);
        frag.setArguments(args);
        return frag;
    }


    //Callbacks para devolver ingrediente e QTD usada
    public interface NoticeDialogListener{
        void onDialogPositiveClick(Float qtdUsada, Ingrediente ingrediente);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener mListener;
    float qtdUsada;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        //Pega o objeto selecionado
        final Ingrediente ingrediente = (Ingrediente) getArguments().getSerializable("ingrediente");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_ingrediente_produto,null);

        EditText txtQtdUsada = (EditText) dialogView.findViewById(R.id.txtIngreQtdUsada);

        qtdUsada = Float.parseFloat(txtQtdUsada.getText().toString());


        builder.setView(dialogView)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogPositiveClick(qtdUsada,ingrediente);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogNegativeClick(DialogListIngredienteProduto.this);
                    }
                });


        return builder.create();

    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


}
