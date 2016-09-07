package br.com.pandaria.Dao;

import android.provider.BaseColumns;


public final class VendaContract {

    private VendaContract(){}

    public static class Venda implements BaseColumns {
        public static final String NOME_TABELA = "venda";
        public static final String NOME_COLUNA_DATA = "date_venda";

    }


    private static final String DATE_TYPE = " NUMERIC";
    private static final String COMMA_SEP = ",";

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Venda.NOME_TABELA + " (" +
                    Venda._ID + " INTEGER PRIMARY KEY," +
                    Venda.NOME_COLUNA_DATA + DATE_TYPE +
                    " )";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Venda.NOME_TABELA;
}
