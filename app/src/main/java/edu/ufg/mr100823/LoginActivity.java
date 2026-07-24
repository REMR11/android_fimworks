package edu.ufg.mr100823;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etClave;
    private ImageButton ibLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClave);
        ibLogin = findViewById(R.id.ibLogin);

        ibLogin.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString().trim();
            String clave = etClave.getText().toString();

            if (usuario.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese un usuario", Toast.LENGTH_SHORT).show();
                return;
            }

            if (clave.length() >= 6) {
                Toast.makeText(this, "Bienvenido al sistema, " + usuario, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error: La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        });
    }
}