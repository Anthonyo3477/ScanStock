package com.example.proyectoprueba.controller;

import android.content.Intent;
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

        // Creacion del Adapter
        adapter = new productoAdapter(listaProducto, producto -> {

            Intent intent = new Intent(ListarProductos.this, modificarProducto.class);

            intent.putExtra("idProducto", producto.getId());
            intent.putExtra("nombre", producto.getNombre());
            intent.putExtra("marca", producto.getMarca());
            intent.putExtra("categoria", producto.getCategoria());
            intent.putExtra("fechaCaducidad", producto.getFechaCaducidad());
            intent.putExtra("codigoBarras", producto.getCodigoBarras());
            intent.putExtra("cantidad", producto.getCantidad());
            intent.putExtra("stockBodega", producto.getStockBodega());
            intent.putExtra("stockGondola", producto.getStockGondola());

            startActivity(intent);

        },
                // Eliminar
                producto -> {

                    db.collection("producto").document(producto.getId()).delete().addOnSuccessListener(unused -> {
                        Toast.makeText(this, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show();
                        cargarProductosPorCategoria(getIntent().getStringExtra("categoria")
                        );
                    }).addOnFailureListener(e -> {
                        Toast.makeText(this, "Error al eliminar el producto", Toast.LENGTH_SHORT).show();
                    });
                }
        );

        // Recycle adapter
        recyclerProductos.setAdapter(adapter);

        // Firebase
        db = FirebaseFirestore.getInstance();

        String categoriaSelecionada = getIntent().getStringExtra("categoria");
        cargarProductosPorCategoria(categoriaSelecionada);
        Toast.makeText(this, "Categoria: " + categoriaSelecionada, Toast.LENGTH_SHORT).show();
        btnVolver.setOnClickListener(v -> finish());

    }

    private void cargarProductosPorCategoria(String categoria) {

        db.collection("producto")
                .whereEqualTo("categoria", categoria)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    listaProducto.clear();

                    for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                        Producto producto = doc.toObject(Producto.class);
                        if (producto != null) {
                            producto.setId(doc.getId());
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