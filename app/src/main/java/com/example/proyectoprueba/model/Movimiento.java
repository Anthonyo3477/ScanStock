package com.example.proyectoprueba.model;

public class Movimiento {

    private String id;
    private String idProducto;
    private String nombreProducto;
    private String usuario;
    private String accion;
    private String destino;
    private int cantidad;
    private int stockAntes;
    private int stockDespues;
    private long fecha;

    public Movimiento() {
    }

    public Movimiento(String id, String idProducto, String nombreProducto, String usuario, String accion, String destino, int cantidad, int stockAntes, int stockDespues, long fecha) {
        this.id = id;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.usuario = usuario;
        this.accion = accion;
        this.destino = destino;
        this.cantidad = cantidad;
        this.stockAntes = stockAntes;
        this.stockDespues = stockDespues;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStockAntes() {
        return stockAntes;
    }

    public void setStockAntes(int stockAntes) {
        this.stockAntes = stockAntes;
    }

    public int getStockDespues() {
        return stockDespues;
    }

    public void setStockDespues(int stockDespues) {
        this.stockDespues = stockDespues;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "id='" + id + '\'' +
                ", idProducto='" + idProducto + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", usuario='" + usuario + '\'' +
                ", accion='" + accion + '\'' +
                ", destino='" + destino + '\'' +
                ", cantidad=" + cantidad +
                ", stockAntes=" + stockAntes +
                ", stockDespues=" + stockDespues +
                ", fecha=" + fecha +
                '}';
    }
}
