package edu.ufg.mr100823;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class CheckBoxActivity extends AppCompatActivity {

    private EditText et1, et2;
    private CheckBox cb1, cb2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
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
        String resu = "";

        if (cb1.isChecked()) {
            int suma = n1 + n2;
            resu = "La suma es: " + suma + " / ";
        }
        if (cb2.isChecked()) {
            int resta = n1 - n2;
            resu = resu + "La resta es: " + resta;
        }

        if (resu.isEmpty()) {
            tv3.setText("Seleccione al menos una operación");
        } else {
            tv3.setText(resu);
        }
    }
}
