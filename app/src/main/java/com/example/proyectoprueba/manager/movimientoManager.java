package com.example.proyectoprueba.manager;

import com.example.proyectoprueba.model.Movimiento;
import com.google.firebase.firestore.FirebaseFirestore;

public class movimientoManager {

    private final FirebaseFirestore db;

    public movimientoManager() {
        db = FirebaseFirestore.getInstance();
    }

    public void registrarMovimiento(String idProducto, String nombreProducto, String usuario, String accion, String destino, int cantidad, int stockAntes, int stockDespues) {

        String idMovimiento = db.collection("movimientos").document().getId();
        Movimiento movimiento = new Movimiento(idMovimiento, idProducto, nombreProducto, usuario, accion, destino, cantidad, stockAntes, stockDespues, System.currentTimeMillis());

        db.collection("movimientos").document(idMovimiento).set(movimiento).addOnSuccessListener(unused -> {
            System.out.println("Movimiento registrado correctamente");
        }).addOnFailureListener(e -> {System.out.println("Error al registrar movimiento: " + e.getMessage());
        });
    }
}