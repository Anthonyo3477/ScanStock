package com.example.proyectoprueba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.Usuario;

import java.util.List;

public class personalAdapter extends RecyclerView.Adapter<personalAdapter.ViewHolder> {

    private List<Usuario> listaPersonal;
    private OnItemClickListener listener;
    private Button btnModificarPersonal, btnEliminarPersonal, btnVolver;

    public interface OnItemClickListener {
        void onItemClick(Usuario usuario);
    }

    public personalAdapter(List<Usuario> listaPersonal, OnItemClickListener listener) {
        this.listaPersonal = listaPersonal;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_personal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Usuario personal = listaPersonal.get(position);

        Button btnModificarPersonal = holder.itemView.findViewById(R.id.btnModificarPersonal);
        Button btnEliminarPersonal = holder.itemView.findViewById(R.id.btnEliminarPersonal);
        Button btnVolver = holder.itemView.findViewById(R.id.btnVolver);

        holder.txtNombrePersonal.setText(personal.getNombre());
        holder.txtRut.setText("Rut: " + personal.getRut());
        holder.txtDireccion.setText("Dirección: " + personal.getDireccion());
        holder.txtCorreo.setText("Correo: " + personal.getCorreo());
        holder.txtRol.setText("Rol: " + personal.getRol());

        btnModificarPersonal.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(personal);
            }
        });

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