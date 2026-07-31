package edu.ufg.mr100823;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ImageButtonActivity extends AppCompatActivity {

    private TextView tvEstadoLlamada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button);

        tvEstadoLlamada = findViewById(R.id.tvEstadoLlamada);
    }

    // Método vinculado al onClick del ImageButton en el XML
    public void llamar(View view) {
        tvEstadoLlamada.setText("Llamando...");
    }
}
