package com.example.proyectoprueba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;

import java.util.List;

public class AvisosAdapter extends RecyclerView.Adapter<AvisosAdapter.AvisoViewHolder> {

    private List<String> listaAvisos;
    private LayoutInflater inflater;

    // Constructor del adaptador
    public AvisosAdapter(List<String> listaAvisos, Context context) {
        this.listaAvisos = listaAvisos;
        this.inflater = LayoutInflater.from(context);
    }

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

    @Override
    public int getItemCount() {
        return listaAvisos == null ? 0 : listaAvisos.size();
    }

    class AvisoViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewAviso;

        public AvisoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAviso = itemView.findViewById(R.id.textViewAviso);
        }
    }
}
