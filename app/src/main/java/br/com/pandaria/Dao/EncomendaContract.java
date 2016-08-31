package br.com.pandaria.Dao;

import android.provider.BaseColumns;

public class EncomendaContract {

    private EncomendaContract(){}

    public static class Encomenda implements BaseColumns {
        public static final String NOME_TABELA = "encomenda";
        public static final String NOME_COLUNA_CLIENTE = "cliente";
        public static final String NOME_COLUNA_VALOR = "valor";
        public static final String NOME_COLUNA_DESCRICAO = "DESCRICAO";
        public static final String NOME_COLUNA_IS_CANCELADA = "is_cancelada";
        public static final String NOME_COLUNA_FK_VENDA = "id_venda";


    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String FLOAT_TYPE = " REAL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String BOOLEAN_TYPE = " NUMERIC";
    private static final String COMMA_SEP = ",";

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Encomenda.NOME_TABELA + " (" +
                    Encomenda._ID + " INTEGER PRIMARY KEY," +
                    Encomenda.NOME_COLUNA_CLIENTE + TEXT_TYPE + COMMA_SEP +
                    Encomenda.NOME_COLUNA_VALOR + FLOAT_TYPE + COMMA_SEP +
                    Encomenda.NOME_COLUNA_DESCRICAO + TEXT_TYPE + COMMA_SEP +
                    Encomenda.NOME_COLUNA_IS_CANCELADA + BOOLEAN_TYPE + COMMA_SEP +
                    Encomenda.NOME_COLUNA_FK_VENDA + INTEGER_TYPE +
                    " )";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Encomenda.NOME_TABELA;
}
