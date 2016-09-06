package br.com.pandaria.Utility;

public class Custo {

    public static float custoPorIngrediente(float preco,float qtdPacote,float qtdUtilizada){

        return (preco * qtdUtilizada) / qtdPacote;

    }

}
