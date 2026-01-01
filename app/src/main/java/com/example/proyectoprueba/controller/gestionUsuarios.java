package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.adapter.personalAdapter;
import com.example.proyectoprueba.model.usuarios;

import java.util.ArrayList;
import java.util.List;

public class gestionUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_usuarios);

        Button btnVolver = findViewById(R.id.btnVolver);
        RecyclerView recyclerPersonal = findViewById(R.id.recyclerPersonal);

        // LayoutManager
        recyclerPersonal.setLayoutManager(new LinearLayoutManager(this));

        // Lista de usuarios (mock)
        List<usuarios> listaPersonal = new ArrayList<>();

        listaPersonal.add(new usuarios(1, "Juan Pérez", "12.345.678-9", "Av. Central 123", "juan@mail.com", "Admin", null));
        listaPersonal.add(new usuarios( 2, "María García", "9.876.543-2", "Calle Sur 456", "maria@mail.com", "Usuario", null));
        listaPersonal.add(new usuarios(3, "Pedro Rodríguez", "4.567.890-1", "Calle Norte 789", "pedro@mail.com", "Usuario", null));


        // Adapter
        personalAdapter adapter = new personalAdapter(listaPersonal);
        recyclerPersonal.setAdapter(adapter);

        // Volver
        btnVolver.setOnClickListener(v -> finish());
    }
}
