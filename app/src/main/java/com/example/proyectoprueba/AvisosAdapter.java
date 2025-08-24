package com.example.proyectoprueba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; // Para mostrar el texto del aviso en cada ítem
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AvisosAdapter extends RecyclerView.Adapter<AvisosAdapter.AvisoViewHolder> {

    private List<String> listaAvisos;
    private LayoutInflater inflater; // Para "inflar" (crear) las vistas de los ítems desde XML

    // Constructor del adaptador
    public AvisosAdapter(List<String> listaAvisos, Context context) {
        this.listaAvisos = listaAvisos;
        this.inflater = LayoutInflater.from(context);
    }

    // Este método se llama cuando RecyclerView necesita crear un nuevo ViewHolder.
    // Un ViewHolder describe una vista de ítem y metadatos sobre su lugar dentro del RecyclerView.
    @NonNull
    @Override
    public AvisoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_aviso, parent, false);
        return new AvisoViewHolder(itemView);
    }
    // Este método se llama para establecer los datos del ítem en el ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull AvisoViewHolder holder, int position) {
        String avisoActual = listaAvisos.get(position);
        holder.textViewAviso.setText(avisoActual);
    }

    // Devuelve el número total de ítems en el conjunto de datos que tiene el adaptador.
    @Override
    public int getItemCount() {
        return listaAvisos == null ? 0 : listaAvisos.size();
    }

    // Clase interna ViewHolder. Contiene las vistas para cada ítem individual de la lista.
    class AvisoViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewAviso;

        public AvisoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAviso = itemView.findViewById(R.id.textViewAviso); // El ID del TextView en item_aviso.xml
        }
    }
}
