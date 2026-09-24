package com.example.proyectoprueba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.Movimiento;
import com.google.firebase.Timestamp; // Se agrega la importación de Firebase Timestamp

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class movimientoAdapter extends RecyclerView.Adapter<movimientoAdapter.MovimientoViewHolder> {
    private final Context context;
    private final List<Movimiento> listaMovimientos;

    public movimientoAdapter(Context context, List<Movimiento> listaMovimientos) {
        this.context = context;
        this.listaMovimientos = listaMovimientos;
    }

    @NonNull
    @Override
    public MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movimientos, parent, false);
        return new MovimientoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovimientoViewHolder holder, int position) {

        Movimiento movimiento = listaMovimientos.get(position);
        holder.txtProducto.setText("Producto: " + movimiento.getNombreProducto());
        holder.txtUsuario.setText("Usuario: " + movimiento.getUsuario());
        holder.txtCodigoBarras.setText("Codigo de Barras: " + movimiento.getCodigoBarras());
        holder.txtAccion.setText("Acción: " + movimiento.getAccion());
        holder.txtDestino.setText("Destino: " + movimiento.getDestino());
        holder.txtCantidad.setText("Cantidad: " + movimiento.getCantidad());
        holder.txtStock.setText("Stock: " + movimiento.getStockAntes() + " → " + movimiento.getStockDespues());
        holder.txtFecha.setText("Fecha: " + convertirFecha(movimiento.getFecha()));
    }

    @Override
    public int getItemCount() {
        return listaMovimientos.size();
    }

    private String convertirFecha(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }
        Date date = timestamp.toDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return formato.format(date);
    }

    public static class MovimientoViewHolder extends RecyclerView.ViewHolder {
        TextView txtProducto, txtUsuario, txtAccion, txtCodigoBarras, txtDestino, txtCantidad, txtStock, txtFecha;

        public MovimientoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProducto = itemView.findViewById(R.id.txtProducto);
            txtUsuario = itemView.findViewById(R.id.txtUsuario);
            txtAccion = itemView.findViewById(R.id.txtAccion);
            txtCodigoBarras = itemView.findViewById(R.id.txtCodigoBarras);
            txtDestino = itemView.findViewById(R.id.txtDestino);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            txtStock = itemView.findViewById(R.id.txtStock);
            txtFecha = itemView.findViewById(R.id.txtFecha);
        }
    }
}