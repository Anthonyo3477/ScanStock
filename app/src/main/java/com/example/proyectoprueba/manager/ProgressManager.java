package com.example.proyectoprueba.manager;

import android.view.View;
import android.widget.ProgressBar;

public class ProgressManager {

    private final ProgressBar progressBar;
    private final View[] componentes;

    public ProgressManager(ProgressBar progressBar, View... componentes) {

        this.progressBar = progressBar;
        this.componentes = componentes;

    }

    public void mostrar() {

        progressBar.setVisibility(View.VISIBLE);
        for (View view : componentes) {
            if (view != null) {
                view.setEnabled(false);
            }
        }
    }

    public void ocultar() {

        progressBar.setVisibility(View.GONE);
        for (View view : componentes) {
            if (view != null) {
                view.setEnabled(true);
            }
        }
    }
}