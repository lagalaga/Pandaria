package br.com.pandaria.Dao;

import android.provider.BaseColumns;

public final class IngredienteContract {

    private IngredienteContract(){}

    public static class Ingrediente implements BaseColumns{
        public static final String NOME_TABELA = "ingrediente";
        public static final String NOME_COLUNA_NOME = "nome";
        public static final String NOME_COLUNA_TIPO = "tipo";
        public static final String NOME_COLUNA_QTD = "qtddopacote";

    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Ingrediente.NOME_TABELA + " (" +
                    Ingrediente._ID + " INTEGER PRIMARY KEY," +
                    Ingrediente.NOME_COLUNA_NOME + TEXT_TYPE + COMMA_SEP +
                    Ingrediente.NOME_COLUNA_TIPO + TEXT_TYPE + COMMA_SEP +
                    Ingrediente.NOME_COLUNA_QTD + FLOAT_TYPE + " )";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Ingrediente.NOME_TABELA;
}
