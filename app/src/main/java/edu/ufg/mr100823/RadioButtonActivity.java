package edu.ufg.mr100823;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class RadioButtonActivity extends AppCompatActivity {

    private EditText et1, et2;
    private RadioButton r1, r2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        tv3 = findViewById(R.id.tv3);
    }

    public void operar(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();
        
        if (valor1.isEmpty() || valor2.isEmpty()) {
            tv3.setText("Por favor ingrese ambos valores");
            return;
        }

        int n1 = Integer.parseInt(valor1);
        int n2 = Integer.parseInt(valor2);

        if (r1.isChecked()) {
            int suma = n1 + n2;
            tv3.setText("Resultado: " + suma);
        } else if (r2.isChecked()) {
            int resta = n1 - n2;
            tv3.setText("Resultado: " + resta);
        } else {
            tv3.setText("Seleccione una operación");
        }
    }
}
