package edu.ufg.mr100823;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {

    private TextView tvResultadoPoblacion;
    private ListView lvPaises;

    private String[] paises = {
            "Antigua y Barbuda", "Argentina", "Bahamas", "Barbados", "Belice", "Bolivia", "Brasil", "Canadá", "Chile", "Colombia",
            "Costa Rica", "Cuba", "Dominica", "Ecuador", "El Salvador", "Estados Unidos", "Granada", "Guatemala", "Guyana", "Haití",
            "Honduras", "Jamaica", "México", "Nicaragua", "Panamá", "Paraguay", "Perú", "República Dominicana", "San Cristóbal y Nieves", "San Vicente y las Granadinas",
            "Santa Lucía", "Surinam", "Trinidad y Tobago", "Uruguay", "Venezuela"
    };

    private String[] habitantes = {
            "93,219", "45.8 millones", "407,906", "281,200", "405,272", "12.08 millones", "214.3 millones", "38.2 millones", "19.49 millones", "51.52 millones",
            "5.18 millones", "11.3 millones", "72,412", "17.8 millones", "6.33 millones", "331.9 millones", "124,610", "17.11 millones", "804,567", "11.45 millones",
            "10.12 millones", "2.82 millones", "126.7 millones", "6.85 millones", "4.35 millones", "6.7 millones", "33.72 millones", "11.12 millones", "47,606", "104,332",
            "179,651", "612,985", "1.52 millones", "3.42 millones", "28.2 millones"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        tvResultadoPoblacion = findViewById(R.id.tvResultadoPoblacion);
        lvPaises = findViewById(R.id.lvPaises);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paises);
        lvPaises.setAdapter(adapter);

        lvPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String paisSeleccionado = (String) lvPaises.getItemAtPosition(position);
                String poblacion = habitantes[position];
                String mensaje = "Población de " + paisSeleccionado + " es: " + poblacion;
                
                // Actualizar TextView superior
                tvResultadoPoblacion.setText(mensaje);
                
                // Mostrar Toast en la parte inferior
                Toast.makeText(ListViewActivity.this, "Seleccionaste: " + paisSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
