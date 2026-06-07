package com.example.proyectoprueba.model;

public class Notificacion {

    private String idProducto;
    private String mensaje;
    private String prioridades;
    private String tipo;      // Si es de bodega o de gondola
    private String estado;    // Si esta pendiente o resuelta

    public Notificacion() {
    }

    public Notificacion( String idProducto, String mensaje, String prioridades, String tipo, String estado) {
        this.idProducto = idProducto;
        this.mensaje = mensaje;
        this.prioridades = prioridades;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "idProducto='" + idProducto + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", prioridades='" + prioridades + '\'' +
                ", tipo='" + tipo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}