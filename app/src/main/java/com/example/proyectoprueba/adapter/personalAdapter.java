package com.example.proyectoprueba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.usuarios;

import java.util.List;

public class personalAdapter extends RecyclerView.Adapter<personalAdapter.ViewHolder> {

    private List<usuarios> listaPersonal;


    public personalAdapter(List<usuarios> listaPersonal) {
        this.listaPersonal = listaPersonal;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        usuarios personal = listaPersonal.get(position);

        holder.txtNombrePersonal.setText(personal.getNombre());
        holder.txtRut.setText("Rut: " + personal.getRut());
        holder.txtDireccion.setText("Dirección: " + personal.getDireccion());
        holder.txtCorreo.setText("Correo: " + personal.getCorreo());
        holder.txtRol.setText("Rol: " + personal.getRol());

    }

    @Override
    public int getItemCount() {
        return listaPersonal.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombrePersonal, txtRut, txtDireccion, txtCorreo, txtRol;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombrePersonal = itemView.findViewById(R.id.txtNombreUsuario);
            txtRut = itemView.findViewById(R.id.txtRut);
            txtDireccion = itemView.findViewById(R.id.txtDireccion);
            txtCorreo = itemView.findViewById(R.id.txtCorreo);
            txtRol = itemView.findViewById(R.id.txtRol);
        }
    }
}

