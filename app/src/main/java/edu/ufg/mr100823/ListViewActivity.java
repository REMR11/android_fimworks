package edu.ufg.mr100823;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private TextView tvResultadoPoblacion;
    private ListView lvPaises;
    private Button btnOrdenAlfabetico, btnOrdenPoblacion;

    // Clase interna para manejar la relación entre país y su población numérica
    private static class Pais {
        String nombre;
        String poblacionStr;
        double poblacionNum;

        Pais(String nombre, String poblacionStr) {
            this.nombre = nombre;
            this.poblacionStr = poblacionStr;
            this.poblacionNum = parsePoblacion(poblacionStr);
        }

        private double parsePoblacion(String str) {
            try {
                // Normalización: quitamos comas y convertimos "millones" a valor numérico para comparar
                String clean = str.replace(",", "").replace(" millones", "");
                double val = Double.parseDouble(clean);
                if (str.contains("millones")) {
                    val *= 1000000;
                }
                return val;
            } catch (Exception e) {
                return 0;
            }
        }

        @Override
        public String toString() {
            // Este es el texto que el ArrayAdapter mostrará en el ListView
            return nombre;
        }
    }

    private List<Pais> listaPaises;
    private ArrayAdapter<Pais> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        tvResultadoPoblacion = findViewById(R.id.tvResultadoPoblacion);
        lvPaises = findViewById(R.id.lvPaises);
        btnOrdenAlfabetico = findViewById(R.id.btnOrdenAlfabetico);
        btnOrdenPoblacion = findViewById(R.id.btnOrdenPoblacion);

        inicializarDatos();

        // Usamos ArrayAdapter con la lista de objetos Pais
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPaises);
        lvPaises.setAdapter(adapter);

        lvPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtenemos el objeto Pais directamente desde el adaptador
                Pais seleccionado = adapter.getItem(position);
                String mensaje = "Población de " + seleccionado.nombre + " es: " + seleccionado.poblacionStr;
                
                tvResultadoPoblacion.setText(mensaje);
                Toast.makeText(ListViewActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        // Evento para ordenar Alfabéticamente usando el algoritmo de Burbuja (Manual)
        btnOrdenAlfabetico.setOnClickListener(v -> {
            ordenarBurbujaAlfabetico(listaPaises);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Ordenado (Burbuja): A-Z", Toast.LENGTH_SHORT).show();
        });

        // Evento para ordenar por Población usando el algoritmo de Burbuja (Manual)
        btnOrdenPoblacion.setOnClickListener(v -> {
            ordenarBurbujaPoblacion(listaPaises);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Ordenado (Burbuja): Mayor Población", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Algoritmo de Ordenamiento Manual: Método de Burbuja (Bubble Sort)
     * Ordena la lista de países alfabéticamente (A-Z).
     */
    private void ordenarBurbujaAlfabetico(List<Pais> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).nombre.compareToIgnoreCase(lista.get(j + 1).nombre) > 0) {
                    // Intercambio (Swap)
                    Pais temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Algoritmo de Ordenamiento Manual: Método de Burbuja (Bubble Sort)
     * Ordena la lista de países por cantidad de población (Descendente: Mayor a Menor).
     */
    private void ordenarBurbujaPoblacion(List<Pais> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).poblacionNum < lista.get(j + 1).poblacionNum) {
                    // Intercambio (Swap)
                    Pais temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    private void inicializarDatos() {
        String[] nombres = {
                "Antigua y Barbuda", "Argentina", "Bahamas", "Barbados", "Belice", "Bolivia", "Brasil", "Canadá", "Chile", "Colombia",
                "Costa Rica", "Cuba", "Dominica", "Ecuador", "El Salvador", "Estados Unidos", "Granada", "Guatemala", "Guyana", "Haití",
                "Honduras", "Jamaica", "México", "Nicaragua", "Panamá", "Paraguay", "Perú", "República Dominicana", "San Cristóbal y Nieves", "San Vicente y las Granadinas",
                "Santa Lucía", "Surinam", "Trinidad y Tobago", "Uruguay", "Venezuela"
        };

        String[] habitantes = {
                "93,219", "45.8 millones", "407,906", "281,200", "405,272", "12.08 millones", "214.3 millones", "38.2 millones", "19.49 millones", "51.52 millones",
                "5.18 millones", "11.3 millones", "72,412", "17.8 millones", "6.33 millones", "331.9 millones", "124,610", "17.11 millones", "804,567", "11.45 millones",
                "10.12 millones", "2.82 millones", "126.7 millones", "6.85 millones", "4.35 millones", "6.7 millones", "33.72 millones", "11.12 millones", "47,606", "104,332",
                "179,651", "612,985", "1.52 millones", "3.42 millones", "28.2 millones"
        };

        listaPaises = new ArrayList<>();
        for (int i = 0; i < nombres.length; i++) {
            listaPaises.add(new Pais(nombres[i], habitantes[i]));
        }
    }
}
