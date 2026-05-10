package com.example.proyectoprueba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.Producto;

import java.util.List;

public class productoAdapter extends RecyclerView.Adapter<productoAdapter.ViewHolder> {

    private List<Producto> listaProductos;
    private OnItemClickListener listener;

    // Interface
    public interface OnItemClickListener {
        void onItemClick(Producto producto);
    }

    // Constructor
    public productoAdapter(List<Producto> listaProductos, OnItemClickListener listener) {
        this.listaProductos = listaProductos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_productos, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Producto producto = listaProductos.get(position);

        holder.txtNombreProducto.setText(producto.getNombre());
        holder.txtCategoria.setText("Categoría: " + producto.getCategoria());
        holder.txtStock.setText("Stock: " + producto.getStockGondola());

        // Boton Modificar
        holder.btnModificarProducto.setOnClickListener(v -> {

            if (listener != null) {
                listener.onItemClick(producto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreProducto, txtCategoria, txtStock;
        Button btnModificarProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreProducto = itemView.findViewById(R.id.txtNombreProducto);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtStock = itemView.findViewById(R.id.txtStock);

            btnModificarProducto = itemView.findViewById(R.id.btnModificarProducto);
        }
    }
}