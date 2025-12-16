package com.example.proyectoprueba.model;

import java.security.PrivateKey;
import java.util.Objects;

public class Producto {

    private int idProducto;
    private String nombre;
    private String categoria;
    private String ubicacionGondola;
    private String fechaActualizacion;
    private int stockBodega;
    private int stockGondola;
    private int stockMinimo;

    public Producto(int idProducto, String nombre, String categoria, String ubicacionGondola, String fechaActualizacion,
                    int stockBodega, int stockGondola, int stockMinimo) {

        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacionGondola = ubicacionGondola;
        this.fechaActualizacion = fechaActualizacion;
        this.stockBodega = stockBodega;
        this.stockGondola = stockGondola;
        this.stockMinimo = stockMinimo;

    }

    public Producto(){
    }
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUbicacionGondola() {
        return ubicacionGondola;
    }

    public void setUbicacionGondola(String ubicacionGondola) {
        this.ubicacionGondola = ubicacionGondola;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ubicacionGondola='" + ubicacionGondola + '\'' +
                ", fechaActualizacion='" + fechaActualizacion + '\'' +
                ", stockBodega=" + stockBodega +
                ", stockGondola=" + stockGondola +
                ", stockMinimo=" + stockMinimo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return idProducto == producto.idProducto && stockBodega == producto.stockBodega && stockGondola == producto.stockGondola && stockMinimo == producto.stockMinimo && Objects.equals(nombre, producto.nombre) && Objects.equals(categoria, producto.categoria) && Objects.equals(ubicacionGondola, producto.ubicacionGondola) && Objects.equals(fechaActualizacion, producto.fechaActualizacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, nombre, categoria, ubicacionGondola, fechaActualizacion, stockBodega, stockGondola, stockMinimo);
    }
}
