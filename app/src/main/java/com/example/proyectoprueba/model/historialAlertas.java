package com.example.proyectoprueba.model;

public class historialAlertas {

    private String idProducto;
    private String nombre;
    private String categoria;
    private Long codigoBarras;
    private Integer cantidadFaltante;
    private Integer stockBodega;
    private Integer stockGondola;
    private String estado;

    public historialAlertas() {
    }

    public historialAlertas(String idProducto, String nombre, String categoria, Long codigoBarras, Integer cantidadFaltante, Integer stockBodega, Integer stockGondola, String estado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.codigoBarras = codigoBarras;
        this.cantidadFaltante = cantidadFaltante;
        this.stockBodega = stockBodega;
        this.stockGondola = stockGondola;
        this.estado = estado;
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

    public Long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Long codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getCantidadFaltante() {
        return cantidadFaltante;
    }

    public void setCantidadFaltante(Integer cantidadFaltante) {
        this.cantidadFaltante = cantidadFaltante;
    }

    public Integer getStockBodega() {
        return stockBodega;
    }

    public void setStockBodega(Integer stockBodega) {
        this.stockBodega = stockBodega;
    }

    public Integer getStockGondola() {
        return stockGondola;
    }

    public void setStockGondola(Integer stockGondola) {
        this.stockGondola = stockGondola;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "historialAlertas{" +
                "idProducto='" + idProducto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", codigoBarras=" + codigoBarras +
                ", cantidadFaltante=" + cantidadFaltante +
                ", stockBodega=" + stockBodega +
                ", stockGondola=" + stockGondola +
                ", estado='" + estado + '\'' +
                '}';
    }
}