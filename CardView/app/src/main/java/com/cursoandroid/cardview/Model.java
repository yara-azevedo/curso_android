package com.cursoandroid.cardview;

public class Model {

    private String nome;
    private String texto;
    private int imagem;

    public Model() {
    }

    public Model(String nome, String texto, int imagem) {
        this.nome = nome;
        this.texto = texto;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
