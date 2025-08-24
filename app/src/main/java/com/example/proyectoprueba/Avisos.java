package com.example.proyectoprueba;

// import de los objetos, estos viene del xml, o tambien puede venir de otro lado como una clase
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Avisos extends AppCompatActivity {

   //Declaracion de Variables
    private RecyclerView recyclerViewAvisos;
    private Button buttonVolver;

    // Adaptador y Lista
    // El Adapter es el puente entre tus datos y cómo se muestran en el RecyclerView.
    private AvisosAdapter avisosAdapter;
    private List<String> listaDeAvisos; // Suponemos que tus avisos son simplemente Strings por ahora.

    // Metodo OnCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Siempre llama al método de la superclase primero
        // Layout
        setContentView(R.layout.avisos);

        // Se inicializan las vistas
        recyclerViewAvisos = findViewById(R.id.recyclerViewAvisos);
        buttonVolver = findViewById(R.id.buttonVolver);

        // Preparacion de la base de datos, esto hace que traigan de una manera los datos desde la DB ojo los avisos solo seran de que falta un producto
        // Tambien pueden haber otros avisos, pero esos avisos serian reuniones, entrada mas temprano, entrada mas tarde, pioridades de algun pasillo en especifico
        listaDeAvisos = new ArrayList<>();
        listaDeAvisos.add("Falta productos de limpieza" +
                "Marca: Clorinda" +
                "Cantidad: 5 " +
                "Tamaño: 1 litro");
        listaDeAvisos.add("Aviso importante: Reunión mañana a las 10 AM.");
        listaDeAvisos.add("Recordatorio: Entregar el informe antes del viernes.");
        listaDeAvisos.add("Nueva actualización de la app disponible.");
        listaDeAvisos.add("Mantenimiento programado para el servidor esta noche.");

        // CONFIGURAR EL RECYCLERVIEW
        recyclerViewAvisos.setLayoutManager(new LinearLayoutManager(this));

        // b. Adapter: Crea una instancia de tu adaptador personalizado (que crearemos en un paso separado)
        //    y pásale la lista de datos.
        avisosAdapter = new AvisosAdapter(listaDeAvisos, this);
        recyclerViewAvisos.setAdapter(avisosAdapter);

        // Configuracion Del Boton para Volver
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
