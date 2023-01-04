package com.cursoandroid.recyclerview.model;

public class Filme {

    private String titulo;
    private String subtitulo;
    private String ano;

    public Filme(){

    }

    public Filme(String titulo, String subtitulo, String ano) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
