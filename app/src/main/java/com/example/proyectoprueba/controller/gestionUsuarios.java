package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.adapter.personalAdapter;
import com.example.proyectoprueba.model.Usuario;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class gestionUsuarios extends AppCompatActivity {

    private personalAdapter adapter;
    private List<Usuario> listaPersonal;
    private FirebaseFirestore db;
    private RecyclerView recyclerPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_usuarios);

        // Botones
        Button btnVolver = findViewById(R.id.btnVolver);
        RecyclerView recyclerPersonal = findViewById(R.id.recyclerPersonal);

        // LayoutManager
        recyclerPersonal.setLayoutManager(new LinearLayoutManager(this));

        // Lista de Usuario
        listaPersonal = new ArrayList<>();
        adapter = new personalAdapter(listaPersonal);
        recyclerPersonal.setAdapter(adapter);

        // Firebase
        db = FirebaseFirestore.getInstance();
        cargarPersonal();
        btnVolver.setOnClickListener(v -> finish());
    }

    private void cargarPersonal() {

        db.collection("usuarios").get().addOnSuccessListener(queryDocumentSnapshots -> {

            listaPersonal.clear();

            for(DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                Usuario usuario = doc.toObject(Usuario.class);
                if (usuario != null) {
                    listaPersonal.add(usuario);
                }
            }
            adapter.notifyDataSetChanged();
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "Error al cargar los usuarios", Toast.LENGTH_SHORT).show();
                });
        }
    }
