package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.Producto;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class reportesProductos extends AppCompatActivity {

    private TextView txtProductosBajos, txtProductosCriticos;
    private Button btnGenerarExcel, btnVolver;

    private FirebaseFirestore db;

    private List<Producto> listaProductosReportes;

    private final ActivityResultLauncher<Intent> crearArchivoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            Uri uri = result.getData().getData();
            if (uri != null) {
                generarExcel(uri);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportes_productos);

        db = FirebaseFirestore.getInstance();

        txtProductosBajos = findViewById(R.id.txtProductosBajos);
        txtProductosCriticos = findViewById(R.id.txtProductosCriticos);

        btnGenerarExcel = findViewById(R.id.btnGenerarExcel);
        btnVolver = findViewById(R.id.btnVolver);

        listaProductosReportes = new ArrayList<>();

        btnVolver.setOnClickListener(v -> finish());

        btnGenerarExcel.setOnClickListener(v -> {

            if (listaProductosReportes.isEmpty()) {
                Toast.makeText(this, "No hay productos con stock bajo o crítico", Toast.LENGTH_LONG).show();
                return;
            }
            seleccionarUbicacionExcel();
        });

        cargarResumenInventario();
    }


    private void cargarResumenInventario() {

        db.collection("producto").get().addOnSuccessListener(queryDocumentSnapshots -> {
            int productosBajos = 0;
            int productosCriticos = 0;

            listaProductosReportes.clear();

            for (var document : queryDocumentSnapshots) {

                Producto producto = document.toObject(Producto.class);
                if (producto == null) {
                    continue;
                }

                int stockBodega = producto.getStockBodega();
                        int stockGondola = producto.getStockGondola();
                        int stockTotal = stockBodega + stockGondola;
                        if (stockTotal <= 5) {
                            productosCriticos++;
                            listaProductosReportes.add(producto);
                        }

                        else if (stockTotal <= 20) {
                            productosBajos++;
                            listaProductosReportes.add(producto);
                        }
                    }

                    txtProductosBajos.setText(String.valueOf(productosBajos));
                    txtProductosCriticos.setText(String.valueOf(productosCriticos));

                })
                .addOnFailureListener(e -> {Toast.makeText(this, "Error al cargar los productos", Toast.LENGTH_LONG).show();
                });
    }

    private void seleccionarUbicacionExcel() {

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        intent.putExtra(Intent.EXTRA_TITLE, "Reporte_Stock_" + System.currentTimeMillis() + ".xlsx");
        crearArchivoLauncher.launch(intent);
    }

    // ============================================================
    // Generar Excel
    // ============================================================

    private void generarExcel(Uri uri) {

        Workbook workbook = null;

        try {

            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Productos por Pedir");

            Row encabezado = sheet.createRow(0);
            crearCelda(encabezado, 0, "ESTADO");
            crearCelda(encabezado, 1, "PRODUCTO");
            crearCelda(encabezado, 2, "MARCA");
            crearCelda(encabezado, 3, "CATEGORIA");
            crearCelda(encabezado, 4, "CODIGO DE BARRAS");
            crearCelda(encabezado, 5, "STOCK BODEGA");
            crearCelda(encabezado, 6, "STOCK GONDOLA");
            crearCelda(encabezado, 7, "STOCK TOTAL");

            int fila = 1;

            for (Producto producto : listaProductosReportes) {

                int stockBodega = producto.getStockBodega();
                int stockGondola = producto.getStockGondola();
                int stockTotal = stockBodega + stockGondola;

                String estado;

                if (stockTotal <= 5) {
                    estado = "CRITICO";
                } else {
                    estado = "BAJO";
                }

                Row row = sheet.createRow(fila++);

                crearCelda(row, 0, estado);
                crearCelda(row, 1, producto.getNombre());
                crearCelda(row, 2, producto.getMarca());
                crearCelda(row, 3, producto.getCategoria());
                crearCelda(row, 4, String.valueOf(producto.getCodigoBarras()));
                crearCelda(row, 5, String.valueOf(stockBodega));
                crearCelda(row, 6, String.valueOf(stockGondola));
                crearCelda(row, 7, String.valueOf(stockTotal));
            }

            // Ancho manual de columnas
            sheet.setColumnWidth(0, 15 * 256);
            sheet.setColumnWidth(1, 25 * 256);
            sheet.setColumnWidth(2, 20 * 256);
            sheet.setColumnWidth(3, 20 * 256);
            sheet.setColumnWidth(4, 20 * 256);
            sheet.setColumnWidth(5, 15 * 256);
            sheet.setColumnWidth(6, 15 * 256);
            sheet.setColumnWidth(7, 15 * 256);

            OutputStream outputStream = getContentResolver().openOutputStream(uri);

            if (outputStream == null) {
                Toast.makeText(this, "No se pudo abrir el archivo", Toast.LENGTH_LONG).show();
                return;
            }

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

            Toast.makeText(this, "Excel generado correctamente", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "Error al generar Excel: " + e.getMessage(), Toast.LENGTH_LONG).show();

            e.printStackTrace();

        } finally {

            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void crearCelda(Row fila, int columna, String valor) {
        Cell celda = fila.createCell(columna);
        celda.setCellValue(valor != null ? valor : "");
    }
}