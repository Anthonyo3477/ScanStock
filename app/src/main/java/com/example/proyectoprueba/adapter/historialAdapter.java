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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_historial, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        historialAlertas alerta = listaHistorial.get(position);

        // Nombre y Categoría (Strings)
        holder.txtNombreProducto.setText(alerta.getNombre() != null ? alerta.getNombre() : "Sin nombre");
        holder.txtCategoria.setText(alerta.getCategoria() != null ? alerta.getCategoria() : "Sin categoría");

        // Código de Barras (Long)
        if (alerta.getCodigoBarras() != null) {
            holder.txtCodigo.setText("Código: " + alerta.getCodigoBarras());
            holder.txtCodigo.setVisibility(View.VISIBLE);
        } else {
            holder.txtCodigo.setVisibility(View.GONE);
        }

        // Cantidad Faltante (Integer)
        if (alerta.getCantidadFaltante() != null && alerta.getCantidadFaltante() > 0) {
            holder.txtCantidad.setText("Cantidad Faltante: " + alerta.getCantidadFaltante());
            holder.txtCantidad.setVisibility(View.VISIBLE);
        } else {
            holder.txtCantidad.setVisibility(View.GONE);
        }

        // Stock Bodega (Integer)
        if (alerta.getStockBodega() != null && alerta.getStockBodega() > 0) {
            holder.txtBodega.setText("Stock Bodega: " + alerta.getStockBodega());
            holder.txtBodega.setVisibility(View.VISIBLE);
        } else {
            holder.txtBodega.setVisibility(View.GONE);
        }

        // Stock Góndola (Integer)
        if (alerta.getStockGondola() != null && alerta.getStockGondola() > 0) {
            holder.txtGondola.setText("Stock Góndola: " + alerta.getStockGondola());
            holder.txtGondola.setVisibility(View.VISIBLE);
        } else {
            holder.txtGondola.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listaHistorial != null ? listaHistorial.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreProducto;
        TextView txtEstado;
        TextView txtCodigo;
        TextView txtCategoria;
        TextView txtCantidad;
        TextView txtBodega;
        TextView txtGondola;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreProducto = itemView.findViewById(R.id.txtNombreProducto);
            txtEstado = itemView.findViewById(R.id.txtEstado);
            txtCodigo = itemView.findViewById(R.id.txtCodigo);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            txtBodega = itemView.findViewById(R.id.txtBodega);
            txtGondola = itemView.findViewById(R.id.txtGondola);
        }
    }
}