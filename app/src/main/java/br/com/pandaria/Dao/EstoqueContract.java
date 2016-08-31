package br.com.pandaria.Dao;

import android.provider.BaseColumns;

public class EstoqueContract {

    private EstoqueContract(){}

    public static class Estoque implements BaseColumns {
        public static final String NOME_TABELA = "estoque";
        public static final String NOME_COLUNA_QTD = "qtd";
        public static final String NOME_COLUNA_DATA_ENTRADA = "data_entrada";
        public static final String NOME_COLUNA_PRECO_UNIT = "preco_unit";
        public static final String NOME_COLUNA_FK_INGREDIENTE = "id_ingrediente";


    }

    private static final String FLOAT_TYPE = " REAL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATE_TYPE = " NUMERIC";
    private static final String COMMA_SEP = ",";

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estoque.NOME_TABELA + " (" +
                    Estoque._ID + " INTEGER PRIMARY KEY," +
                    Estoque.NOME_COLUNA_QTD + INTEGER_TYPE + COMMA_SEP +
                    Estoque.NOME_COLUNA_DATA_ENTRADA + DATE_TYPE + COMMA_SEP +
                    Estoque.NOME_COLUNA_PRECO_UNIT + FLOAT_TYPE +COMMA_SEP +
                    Estoque.NOME_COLUNA_FK_INGREDIENTE + INTEGER_TYPE +
                    " )";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estoque.NOME_TABELA;
}
