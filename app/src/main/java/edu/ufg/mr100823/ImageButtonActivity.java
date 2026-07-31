package edu.ufg.mr100823;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ImageButtonActivity extends AppCompatActivity {

    private EditText etNumero;
    private Button btnSeleccionarContacto;
    private ImageButton ibLlamar, ibBorrar;
    private static final int REQUEST_CALL_PERMISSION = 1;

    // Launcher para seleccionar un contacto
    private final ActivityResultLauncher<Intent> contactPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri contactUri = result.getData().getData();
                    recuperarNumeroContacto(contactUri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button);

        etNumero = findViewById(R.id.etNumero);
        btnSeleccionarContacto = findViewById(R.id.btnSeleccionarContacto);
        ibLlamar = findViewById(R.id.ibLlamar);
        ibBorrar = findViewById(R.id.ibBorrar);

        // Abrir selector de contactos
        btnSeleccionarContacto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            contactPickerLauncher.launch(intent);
        });

        // Botón de llamar
        ibLlamar.setOnClickListener(v -> realizarLlamada());

        // Botón de borrar (último dígito)
        ibBorrar.setOnClickListener(v -> {
            String currentText = etNumero.getText().toString();
            if (currentText.length() > 0) {
                etNumero.setText(currentText.substring(0, currentText.length() - 1));
                etNumero.setSelection(etNumero.getText().length());
            }
        });
        
        // Mantener presionado borrar para limpiar todo
        ibBorrar.setOnLongClickListener(v -> {
            etNumero.setText("");
            return true;
        });
    }

    /**
     * Maneja los clics en los botones del teclado numérico
     */
    public void onDigitClick(View view) {
        Button btn = (Button) view;
        String digit = btn.getText().toString();
        etNumero.append(digit);
    }

    private void realizarLlamada() {
        String numero = etNumero.getText().toString().trim();
        if (numero.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese o seleccione un número", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar permiso de llamada
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            // Permiso ya otorgado, realizar llamada
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + numero));
            startActivity(callIntent);
        }
    }

    private void recuperarNumeroContacto(Uri contactUri) {
        String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number = cursor.getString(numberIndex);
            etNumero.setText(number);
            cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                realizarLlamada();
            } else {
                Toast.makeText(this, "Permiso de llamada denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
