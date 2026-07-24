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
    private EjercicioAdapter adapter;

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

        List<Ejercicio> listaEjercicios = new ArrayList<>();
        // Agregando ejercicios al pipeline
        listaEjercicios.add(new Ejercicio("Calculadora de Suma", "Suma dos números decimales.", SumaActivity.class));
        listaEjercicios.add(new Ejercicio("Autenticación Simulada", "Acceso con ImageButton y validación de clave.", LoginActivity.class));

        adapter = new EjercicioAdapter(listaEjercicios, this);
        rvEjercicios.setAdapter(adapter);
    }
}
