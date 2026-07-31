package edu.ufg.mr100823;

import java.util.List;

public class Seccion {
    private String titulo;
    private List<Ejercicio> ejercicios;
    private boolean expandida;

    public Seccion(String titulo, List<Ejercicio> ejercicios) {
        this.titulo = titulo;
        this.ejercicios = ejercicios;
        this.expandida = true; // Por defecto expandida
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public boolean isExpandida() {
        return expandida;
    }

    public void setExpandida(boolean expandida) {
        this.expandida = expandida;
    }
}
