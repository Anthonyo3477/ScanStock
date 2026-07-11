package com.example.proyectoprueba.manager;

import com.example.proyectoprueba.model.Producto;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class alertasManager {

    private static final int STOCK_CRITICO = 2;
    private static final int STOCK_BAJO = 5;

    private final FirebaseFirestore db;

    public alertasManager() {
        db = FirebaseFirestore.getInstance();
    }

    //====================================
    // Verifica los Productos
    //====================================
    public void verificarProducto(Producto producto) {

        if (producto == null || producto.getId() == null) {
            return;
        }
        verificarBodega(producto);
        verificarGondola(producto);
    }

    //====================================
    // VERIFICAR BODEGA
    //====================================
    private void verificarBodega(Producto producto) {

        if (producto.getStockBodega() <= STOCK_CRITICO) {
            String mensaje =
                    "Stock Crítico - Bodega: "
                            + producto.getNombre()
                            + " ("
                            + producto.getStockBodega()
                            + ")";

            guardarAlertaSiNoExiste(producto,"Bodega", mensaje,"Critica");

        } else if (producto.getStockBodega() <= STOCK_BAJO) {

            String mensaje =
                    "Stock Bajo - Bodega: "
                            + producto.getNombre()
                            + " ("
                            + producto.getStockBodega()
                            + ")";

            guardarAlertaSiNoExiste(producto,"Bodega", mensaje,"Baja");
        } else {
            resolverAlertas(producto, "Bodega");
        }
    }

    //====================================
    // VERIFICAR GÓNDOLA
    //====================================
    private void verificarGondola(Producto producto) {

        if (producto.getStockGondola() <= STOCK_CRITICO) {
            String mensaje =
                    "Stock Crítico - Góndola: "
                            + producto.getNombre()
                            + " ("
                            + producto.getStockGondola()
                            + ")";

            guardarAlertaSiNoExiste(producto,"Gondola", mensaje,"Critica");

        } else if (producto.getStockGondola() <= STOCK_BAJO) {
            String mensaje =
                    "Stock Bajo - Góndola: "
                            + producto.getNombre()
                            + " ("
                            + producto.getStockGondola()
                            + ")";

            guardarAlertaSiNoExiste(producto,"Gondola", mensaje, "Baja");
        } else {
            resolverAlertas(producto, "Gondola");
        }
    }

    //====================================
    // GUARDAR ALERTA
    //====================================
    private void guardarAlerta(Producto producto, String tipo, String mensaje, String prioridad){

        Map<String,Object> alerta = new HashMap<>();

        alerta.put("idProducto", producto.getId());
        alerta.put("codigoBarras", producto.getCodigoBarras());
        alerta.put("nombre", producto.getNombre());
        alerta.put("tipo", tipo);
        alerta.put("mensaje", mensaje);
        alerta.put("prioridad", prioridad);
        alerta.put("estado","pendiente");
        alerta.put("origen","Automatica");
        alerta.put("fecha",System.currentTimeMillis());
        db.collection("alertas").add(alerta);
    }

    //====================================
    // EVITAR ALERTAS DUPLICADAS
    //====================================
    private void guardarAlertaSiNoExiste(Producto producto, String tipo, String mensaje, String prioridad) {
        db.collection("alertas").whereEqualTo("idProducto", producto.getId()).whereEqualTo("tipo", tipo).whereEqualTo("estado", "pendiente").get().addOnSuccessListener(query -> {
            if (query.isEmpty()) {
                guardarAlerta(producto, tipo, mensaje, prioridad);
            }
        });
    }

    //====================================
    // RESOLVER ALERTAS
    //====================================
    private void resolverAlertas(Producto producto, String tipo) {
        db.collection("alertas").whereEqualTo("idProducto", producto.getId()).whereEqualTo("tipo", tipo).whereEqualTo("estado", "pendiente").get().addOnSuccessListener(query -> {
            for (var document : query.getDocuments()) {
                document.getReference().update("estado", "resuelta");
            }
        });
    }
}