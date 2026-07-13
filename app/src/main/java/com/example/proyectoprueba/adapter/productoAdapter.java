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
    private OnItemClickListener listenerModificar;
    private OnDeleteClickListener listenerEliminar;

    // Interface para Modificar
    public interface OnItemClickListener {
        void onItemClick(Producto producto);
    }

    // Interface para Eliminar
    public interface OnDeleteClickListener {
        void onDeleteClick(Producto producto);
    }

    // Constructor
    public productoAdapter(List<Producto> listaProductos, OnItemClickListener listenerModificar, OnDeleteClickListener listenerEliminar) {
        this.listaProductos = listaProductos;
        this.listenerModificar = listenerModificar;
        this.listenerEliminar = listenerEliminar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Producto producto = listaProductos.get(position);

        holder.txtNombreProducto.setText(producto.getNombre());
        holder.txtCategoria.setText("Categoría: " + producto.getCategoria());
        holder.txtStockBodega.setText("Stock Bodega: " + producto.getStockBodega());
        holder.txtStockGondola.setText("Stock Gondola: " + producto.getStockGondola());

        // Botón Modificar
        holder.btnModificarProducto.setOnClickListener(v -> {

            if (listenerModificar != null) {
                listenerModificar.onItemClick(producto);
            }
        });

        // Botón Eliminar
        holder.btnEliminarProducto.setOnClickListener(v -> {

            if (listenerEliminar != null) {
                listenerEliminar.onDeleteClick(producto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreProducto, txtCategoria, txtStockGondola, txtStockBodega;
        Button btnModificarProducto, btnEliminarProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreProducto = itemView.findViewById(R.id.txtNombreProducto);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtStockGondola = itemView.findViewById(R.id.txtStockGondola);
            txtStockBodega = itemView.findViewById(R.id.txtStockBodega);

            btnModificarProducto = itemView.findViewById(R.id.btnModificarProducto);
            btnEliminarProducto = itemView.findViewById(R.id.btnEliminarProducto);
        }
    }
}