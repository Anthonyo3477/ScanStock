package com.example.proyectoprueba.model;

public class alertasManual {

    private String nombre;
    private String categoria;
    private String codigoBarras;
    private int cantidad;
    private int stockBodega;
    private int stockGondola;
    private long fecha;

    public alertasManual() {
    }

    public alertasManual(String nombre, String categoria, String codigoBarras, int cantidad, int stockBodega, int stockGondola, long fecha) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.codigoBarras = codigoBarras;
        this.cantidad = cantidad;
        this.stockBodega = stockBodega;
        this.stockGondola = stockGondola;
        this.fecha = fecha;
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

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "alertasManual{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", codigoBarras='" + codigoBarras + '\'' +
                ", cantidad=" + cantidad +
                ", stockBodega=" + stockBodega +
                ", stockGondola=" + stockGondola +
                ", fecha=" + fecha +
                '}';
    }
}
