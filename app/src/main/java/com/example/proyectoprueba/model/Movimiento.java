package com.example.proyectoprueba.model;
import com.google.firebase.Timestamp;

public class Movimiento {

    private String id;
    private String idProducto;
    private String nombreProducto;
    private String usuario;
    private String accion;
    private long codigoBarras;
    private String destino;
    private int cantidad;
    private int stockAntes;
    private int stockDespues;
    private Timestamp fecha;

    public Movimiento() {
    }

    public Movimiento(String id, String idProducto, String nombreProducto, String usuario, String accion, Long codigoBarras, String destino, int cantidad, int stockAntes, int stockDespues, Timestamp fecha) {
        this.id = id;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.usuario = usuario;
        this.accion = accion;
        this.codigoBarras = codigoBarras;
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

    public long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(long codigoBarras) {
        this.codigoBarras = codigoBarras;
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
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
                ", codigoBarras=" + codigoBarras +
                ", destino='" + destino + '\'' +
                ", cantidad=" + cantidad +
                ", stockAntes=" + stockAntes +
                ", stockDespues=" + stockDespues +
                ", fecha=" + fecha +
                '}';
    }
}
