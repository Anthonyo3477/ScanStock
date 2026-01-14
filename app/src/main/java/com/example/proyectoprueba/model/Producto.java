package com.example.proyectoprueba.model;

import java.security.PrivateKey;
import java.util.Objects;

public class Producto {

    private String nombre;
    private String marca;
    private String categoria;
    private String fechaCaducidad;
    private int cantidad;
    private int codigoBarras;
    private int stockBodega;
    private int stockGondola;

    public Producto() {
    }

    public Producto(String nombre, String marca, String categoria, String fechaCaducidad, int cantidad, int codigoBarras, int stockBodega, int stockGondola) {
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
        this.codigoBarras = codigoBarras;
        this.stockBodega = stockBodega;
        this.stockGondola = stockGondola;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getStockBodega() {
        return stockBodega;
    }

    public void setStockBodega(int stockBodega) {
        this.stockBodega = stockBodega;
    }

    public int getStockGondola() {
        return stockGondola;
    }

    public void setStockGondola(int stockGondola) {
        this.stockGondola = stockGondola;
    }
    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", categoria='" + categoria + '\'' +
                ", fechaCaducidad='" + fechaCaducidad + '\'' +
                ", cantidad=" + cantidad +
                ", codigoBarras=" + codigoBarras +
                ", stockBodega=" + stockBodega +
                ", stockGondola=" + stockGondola +
                '}';
    }
}
