package edu.ufg.mr100823;

public class Ejercicio {
    private String titulo;
    private String descripcion;
    private Class<?> activityClass;

    public Ejercicio(String titulo, String descripcion, Class<?> activityClass) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.activityClass = activityClass;
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public Class<?> getActivityClass() { return activityClass; }
}
