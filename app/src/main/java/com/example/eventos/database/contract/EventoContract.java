package com.example.eventos.database.contract;

import com.example.eventos.database.Entity.CategoriaEntity;
import com.example.eventos.database.Entity.EventoEntity;

public final class EventoContract {

    private EventoContract(){}

    public static final String criarTabela(){
        return "CREATE TABLE " + EventoEntity.TABLE_NAME + " (" +
                EventoEntity._ID + " INTEGER PRIMARY KEY," +
                EventoEntity.COLUMN_NAME + " TEXT," +
                EventoEntity.COLUMN_LOCAL + " TEXT," +
                EventoEntity.COLUMN_DATA + " TEXT," +
                EventoEntity.COLUMN_NAME_ID_CATEGORIA + " INTEGER," +
                "FOREIGN KEY (" + EventoEntity.COLUMN_NAME_ID_CATEGORIA + ") REFERENCES " +
                CategoriaEntity.TABLE_NAME + "(" + CategoriaEntity._ID + "))";
    }

    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }
}
