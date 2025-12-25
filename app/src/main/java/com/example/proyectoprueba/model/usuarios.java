package com.example.proyectoprueba.model;

import java.util.Date;

public class usuarios {

    private int idUsuario;
    private String correo;
    private String nombre;
    private String rol;
    private Date fechaCreacion;

    public usuarios() {
    }

    public usuarios(int idUsuario, String correo, String nombre, String rol, Date fechaCreacion) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.nombre = nombre;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "usuarios{" +
                "idUsuario=" + idUsuario +
                ", correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}