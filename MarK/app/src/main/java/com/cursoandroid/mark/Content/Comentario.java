package com.cursoandroid.mark.Content;

public class Comentario {
    String comentario;
    int id;

    public Comentario(String comentario, int id) {
        this.comentario = comentario;
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
