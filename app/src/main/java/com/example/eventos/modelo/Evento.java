package com.example.eventos.modelo;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Evento implements Serializable {
    private int id;
    private String nome;
    private String local;
    private String data;
    private Categoria categoria;

    public Evento(int id, String nome, String local, String data, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.data = data;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + '\n' + "Data: " + data
                + '\n' + "Local: " + local+ '\n'
                + "Localidade: " + categoria.getNomeLocal() + '\n';

    }
}
