package com.example.eventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.eventos.database.Entity.CategoriaEntity;
import com.example.eventos.database.Entity.EventoEntity;
import com.example.eventos.modelo.Categoria;
import com.example.eventos.modelo.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS =
            "SELECT evento._id, nome, local, data, idcategoria, nomeLocal, bairro, cidade, publico FROM " +
            EventoEntity.TABLE_NAME +
            " INNER JOIN " + CategoriaEntity.TABLE_NAME + " ON "  + EventoEntity.COLUMN_NAME_ID_CATEGORIA +
            " = " + CategoriaEntity.TABLE_NAME + "." + CategoriaEntity._ID;

    private final String SQL_EVENTO_POR_CATEGORIA =
            "SELECT _id FROM " +
                    EventoEntity.TABLE_NAME +
            " WHERE idcategoria = ?";

    private DBGateway dbGateway;

    public EventoDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_LOCAL, evento.getLocal());
        contentValues.put(EventoEntity.COLUMN_DATA, evento.getData());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_CATEGORIA, evento.getCategoria().getId());
        if (evento.getId() > 0){
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME,
                    contentValues,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,
                null, contentValues) > 0;
    }

    public List<Evento> listar() {
        List<Evento> eventos = new ArrayList<>();

        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_LOCAL));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_DATA));
            int idCategoria = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_CATEGORIA));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLLUMN_NAME_LOCAL_CAT));
            String bairro = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLLUMN_NAME_CIDADE));
            String publico = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLLUMN_NAME_PUBLICO));
            Categoria categoria = new Categoria(idCategoria, nomeLocal, bairro, cidade, Integer.parseInt(publico));
            eventos.add(new Evento(id, nome, local, data, categoria));
        }

        cursor.close();
        return eventos;
    }

    public boolean Excluir(Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_LOCAL, evento.getLocal());
        contentValues.put(EventoEntity.COLUMN_DATA, evento.getData());
        if (evento.getId() > 0){
            return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) >0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,
                null, contentValues) > 0;

    }

    public boolean ExisteEventoPorCategoria(int idCategoria)
    {
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_EVENTO_POR_CATEGORIA, new String[]{String.valueOf(idCategoria)});

        boolean existeEvento = cursor.getCount() > 0;

        cursor.close();

        return existeEvento;
    }



}

