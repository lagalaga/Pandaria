package br.com.pandaria.Dao;

import android.provider.BaseColumns;

public final class DespesaContract {

private DespesaContract(){}

    public static class Despesa implements BaseColumns {
        public static final String NOME_TABELA = "despesa";
        public static final String NOME_COLUNA_DESCRICAO = "descricao";
        public static final String NOME_COLUNA_VALOR = "valor";
        public static final String NOME_COLUNA_DATA = "data";

    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " REAL";
    private static final String DATE_TYPE = " NUMERIC";
    private static final String COMMA_SEP = ",";

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Despesa.NOME_TABELA + " (" +
                    Despesa._ID + " INTEGER PRIMARY KEY," +
                    Despesa.NOME_COLUNA_DESCRICAO + TEXT_TYPE + COMMA_SEP +
                    Despesa.NOME_COLUNA_VALOR + FLOAT_TYPE + COMMA_SEP +
                    Despesa.NOME_COLUNA_DATA + DATE_TYPE + " )";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Despesa.NOME_TABELA;


}
