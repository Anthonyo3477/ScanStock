package com.example.proyectoprueba.model;

public class alertasManual {

    private String idProducto;
    private String nombre;
    private String categoria;
    private int codigoBarras;
    private int cantidadFaltante;
    private int stockBodega;
    private int stockGondola;
    private long fecha;
    private String estado;
    private String tipo;

    public alertasManual() {
    }

    public alertasManual(String idProducto, String nombre, String categoria, int codigoBarras, int cantidadFaltante, int stockBodega, int stockGondola, long fecha, String estado, String tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.codigoBarras = codigoBarras;
        this.cantidadFaltante = cantidadFaltante;
        this.stockBodega = stockBodega;
        this.stockGondola = stockGondola;
        this.fecha = fecha;
        this.estado = estado;
        this.tipo = tipo;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
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

    public int getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getCantidadFaltante() {
        return cantidadFaltante;
    }

    public void setCantidadFaltante(int cantidadFaltante) {
        this.cantidadFaltante = cantidadFaltante;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "alertasManual{" +
                "idProducto='" + idProducto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", codigoBarras=" + codigoBarras +
                ", cantidadFaltante=" + cantidadFaltante +
                ", stockBodega=" + stockBodega +
                ", stockGondola=" + stockGondola +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}