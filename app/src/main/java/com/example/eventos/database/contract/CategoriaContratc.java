package com.example.eventos.database.contract;

import com.example.eventos.database.Entity.CategoriaEntity;

public final class CategoriaContratc {

    private CategoriaContratc(){}

    public static final String criarTabela(){
       return "CREATE TABLE " + CategoriaEntity.TABLE_NAME + " (" +
       CategoriaEntity._ID + " INTEGER PRIMARY KEY, " +
       CategoriaEntity.COLLUMN_NAME_DESCRICAO + " TEXT)";
    }

    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS " + CategoriaEntity.TABLE_NAME;
    }
}
