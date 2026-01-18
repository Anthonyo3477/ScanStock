package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.adapter.productoAdapter;
import com.example.proyectoprueba.model.Producto;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ListarProductos extends AppCompatActivity {

    private productoAdapter adapter;
    private List<Producto> listaProducto;
    private FirebaseFirestore db;
    private RecyclerView recyclerProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_productos);

        // Botones
        Button btnVolver = findViewById(R.id.btnVolver);
        recyclerProductos = findViewById(R.id.recyclerProductos);

        // Configurar RecyclerView
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar lista
        listaProducto = new ArrayList<>();
        adapter = new productoAdapter(listaProducto);
        recyclerProductos.setAdapter(adapter);

        // Firebase
        db = FirebaseFirestore.getInstance();
        cargarProductos();
        btnVolver.setOnClickListener(v -> finish());
    }

    private void cargarProductos() {

        db.collection("producto")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    listaProducto.clear();

                    for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                        Producto producto = doc.toObject(Producto.class);
                        if (producto != null) {
                            listaProducto.add(producto);
                        }
                    }

                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error al cargar los productos", Toast.LENGTH_SHORT).show()
                );
    }
}