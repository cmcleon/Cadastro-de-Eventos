package com.example.eventos.modelo;

import java.io.Serializable;

public class Categoria implements Serializable {

    private int id;
    private String nomeLocal;
    private String bairro;
    private String cidade;
    private int publico;

    public Categoria(int id, String nomeLocal, String bairro, String cidade, int publico) {
        this.id = id;
        this.nomeLocal = nomeLocal;
        this.bairro = bairro;
        this.cidade = cidade;
        this.publico = publico;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getPublico() {
        return publico;
    }

    public void setPublico(int publico) {
        this.publico = publico;
    }

    public Categoria(String nome) { this.nomeLocal = nome;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    @Override
    public String toString(){return this.nomeLocal;}
}
