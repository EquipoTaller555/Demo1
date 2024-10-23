package cl.cazanga.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText correoIngresado = (EditText) findViewById(R.id.correo);
        EditText claveIngresada = (EditText) findViewById(R.id.contrasena);
        Button botonInicio = (Button) findViewById(R.id.botonIniciar);

        String superUser = "admin@admin.cl";
        String superPass = "admin";

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correoCapturado = correoIngresado.getText().toString();
                String claveCapturada = claveIngresada.getText().toString();

                if(correoCapturado.equalsIgnoreCase(superUser) &&
                        claveCapturada.equalsIgnoreCase(superPass)){

                    startActivity(new Intent(MainActivity.this, menuPrincipal.class));
                    finish();

                }else{
                    Toast.makeText(MainActivity.this, "Credenciales Invalidas", Toast.LENGTH_SHORT).show();
                }

                correoIngresado.setText("");
                claveIngresada.setText("");
            }
        });
    }
}