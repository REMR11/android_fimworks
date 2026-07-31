package edu.ufg.mr100823;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvEjercicios;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvEjercicios = findViewById(R.id.rvEjercicios);
        rvEjercicios.setLayoutManager(new LinearLayoutManager(this));

        // Creación de la Sección 1
        List<Ejercicio> ejerciciosSeccion1 = new ArrayList<>();
        ejerciciosSeccion1.add(new Ejercicio("1. Calculadora de Suma", "Suma dos números decimales.", SumaActivity.class));
        ejerciciosSeccion1.add(new Ejercicio("2. Autenticación Simulada", "Acceso con ImageButton y validación de clave.", LoginActivity.class));
        ejerciciosSeccion1.add(new Ejercicio("3. RadioGroup y RadioButton", "Selección única para sumar o restar dos valores.", RadioButtonActivity.class));
        ejerciciosSeccion1.add(new Ejercicio("4. Control CheckBox", "Selección múltiple para sumar y/o restar.", CheckBoxActivity.class));
        ejerciciosSeccion1.add(new Ejercicio("5. Listas y Adaptadores", "Uso de ListView básico (Ejemplo).", MainActivity.class));
        ejerciciosSeccion1.add(new Ejercicio("6. Control Spinner", "Lista desplegable para seleccionar operación matemática.", SpinnerActivity.class));

        // Creación de la Sección 2
        List<Ejercicio> ejerciciosSeccion2 = new ArrayList<>();
        ejerciciosSeccion2.add(new Ejercicio("7. Control ListView", "Lista interactiva de países y población.", ListViewActivity.class));
        ejerciciosSeccion2.add(new Ejercicio("8. Control ImageButton", "Botón con imagen para simular una llamada.", ImageButtonActivity.class));

        List<Seccion> listaSecciones = new ArrayList<>();
        listaSecciones.add(new Seccion("Sección 1: Controles Básicos", ejerciciosSeccion1));
        listaSecciones.add(new Seccion("Sección 2", ejerciciosSeccion2));

        adapter = new HomeAdapter(listaSecciones, this);
        rvEjercicios.setAdapter(adapter);
    }
}
