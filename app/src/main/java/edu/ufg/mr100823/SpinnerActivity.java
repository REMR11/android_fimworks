package edu.ufg.mr100823;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SpinnerActivity extends AppCompatActivity {

    private EditText et1, et2;
    private Spinner spinner1;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        spinner1 = findViewById(R.id.spinner1);
        tvResultado = findViewById(R.id.tvResultado);

        String[] opciones = {"sumar", "restar", "multiplicar", "dividir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
    }

    public void operar(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        if (valor1.isEmpty() || valor2.isEmpty()) {
            tvResultado.setText("Por favor ingrese ambos valores");
            return;
        }

        try {
            double n1 = Double.parseDouble(valor1);
            double n2 = Double.parseDouble(valor2);
            String selec = spinner1.getSelectedItem().toString();
            double res = 0;

            if (selec.equals("sumar")) {
                res = n1 + n2;
            } else if (selec.equals("restar")) {
                res = n1 - n2;
            } else if (selec.equals("multiplicar")) {
                res = n1 * n2;
            } else if (selec.equals("dividir")) {
                if (n2 != 0) {
                    res = n1 / n2;
                } else {
                    tvResultado.setText("Error: División por cero");
                    return;
                }
            }

            tvResultado.setText("Resultado: " + res);
        } catch (NumberFormatException e) {
            tvResultado.setText("Error: Valores numéricos inválidos");
        }
    }
}
