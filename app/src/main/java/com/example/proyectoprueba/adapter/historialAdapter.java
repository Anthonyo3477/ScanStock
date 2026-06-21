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

        holder.txtNombreProducto.setText(alerta.getNombre());
        holder.txtEstado.setText("RESUELTA");
        holder.txtCodigo.setText("Código: " + alerta.getCodigoBarras());
        holder.txtCategoria.setText("Categoría: " + alerta.getCategoria());
        holder.txtCantidad.setText("Cantidad Faltante: " + alerta.getCantidadFaltante());
        holder.txtBodega.setText("Stock Bodega: " + alerta.getStockBodega());
        holder.txtGondola.setText("Stock Góndola: " + alerta.getStockGondola());
    }

    @Override
    public int getItemCount() {
        return listaHistorial.size();
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