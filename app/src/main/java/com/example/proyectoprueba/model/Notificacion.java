package com.example.proyectoprueba.model;

public class Notificacion {

    private String mensaje;
    private String prioridades;

    public Notificacion() {
    }

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
        this.prioridades = prioridades;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPrioridades() {
        return prioridades;
    }

    public void setPrioridades(String prioridades) {
        this.prioridades = prioridades;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "mensaje='" + mensaje + '\'' +
                ", prioridades='" + prioridades + '\'' +
                '}';
    }
}
