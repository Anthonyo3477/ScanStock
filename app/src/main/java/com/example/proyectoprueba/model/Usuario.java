package com.example.proyectoprueba.model;

import java.util.Date;

public class Usuario {

    private String Id;
    private String nombre;
    private String rut;
    private String direccion;
    private String correo;
    private String rol;
    private Date fechaCreacion;

    public Usuario() { }

    public Usuario( String id, String nombre, String rut, String direccion, String correo, String rol, Date fechaCreacion) {
        this.Id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.correo = correo;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", rut='" + rut + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", rol='" + rol + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}