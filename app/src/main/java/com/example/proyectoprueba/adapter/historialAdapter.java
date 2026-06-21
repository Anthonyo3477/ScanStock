package com.example.proyectoprueba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.historialAlertas;

import java.util.List;

public class historialAdapter extends RecyclerView.Adapter<historialAdapter.ViewHolder> {

    private List<historialAlertas> listaHistorial;

    public historialAdapter(List<historialAlertas> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_historial, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        historialAlertas alerta = listaHistorial.get(position);

        String detalle =
                "Producto: " + alerta.getNombre()
                        + "\nCódigo de Barras: " + alerta.getCodigoBarras()
                        + "\nCategoría: " + alerta.getCategoria()
                        + "\nCantidad Faltante: " + alerta.getCantidadFaltante()
                        + "\nStock Bodega: " + alerta.getStockBodega()
                        + "\nStock Góndola: " + alerta.getStockGondola()
                        + "\nEstado: " + alerta.getEstado();

        holder.txtHistorial.setText(detalle);
    }

    @Override
    public int getItemCount() {
        return listaHistorial.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHistorial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHistorial = itemView.findViewById(R.id.txtHistorial);
        }
    }
}