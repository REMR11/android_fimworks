package edu.ufg.mr100823;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SumaActivity extends AppCompatActivity {

    private EditText etNumero1;
    private EditText etNumero2;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);

        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        tvResultado = findViewById(R.id.tvResultado);
    }

    /**
     * Método enlazado mediante el atributo android:onClick en el XML.
     */
    public void calcularSuma(View view) {
        String valor1 = etNumero1.getText().toString().trim();
        String valor2 = etNumero2.getText().toString().trim();

        if (valor1.isEmpty() || valor2.isEmpty()) {
            Toast.makeText(this, "Por favor complete ambos campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(valor1);
            double num2 = Double.parseDouble(valor2);
            double suma = num1 + num2;
            tvResultado.setText("Resultado: " + suma);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingrese números válidos", Toast.LENGTH_SHORT).show();
        }
    }
}
