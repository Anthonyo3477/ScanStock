package com.example.proyectoprueba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.Notificacion;

import java.util.List;

public class notificacionAdapter extends RecyclerView.Adapter<notificacionAdapter.ViewHolder> {

    private List<Notificacion> listaNotificaciones;
    private OnReponerClickListener listener;
    public interface OnReponerClickListener {
        void onReponerClick(Notificacion notificacion);
    }

    public notificacionAdapter(List<Notificacion> listaNotificaciones, OnReponerClickListener listener) {
        this.listaNotificaciones = listaNotificaciones;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notificacion notificacion = listaNotificaciones.get(position);
        holder.txtMensaje.setText(notificacion.getMensaje());

        holder.btnReponer.setOnClickListener(v -> {
            if (listener != null) {
                listener.onReponerClick(notificacion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaNotificaciones.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMensaje;
        Button btnReponer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMensaje = itemView.findViewById(R.id.txtMensaje);
            btnReponer = itemView.findViewById(R.id.btnReponer);
        }
    }
}