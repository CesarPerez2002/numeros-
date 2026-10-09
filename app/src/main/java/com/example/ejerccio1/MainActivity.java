package com.example.ejerccio1;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero1;
    private EditText etNumero2;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular los componentes de la interfaz
        etNumero1 = findViewById(R.id.numero1);
        etNumero2 = findViewById(R.id.numero2);
        tvResultado = findViewById(R.id.resultado);

        Button btnSumar = findViewById(R.id.btnSumar);
        Button btnRestar = findViewById(R.id.btnRestar);
        Button btnDividir = findViewById(R.id.btnDividir);

        // Asignar los eventos a cada botón
        btnSumar.setOnClickListener(v -> realizarOperacion('+'));
        btnRestar.setOnClickListener(v -> realizarOperacion('-'));
        btnDividir.setOnClickListener(v -> realizarOperacion('/'));
    }

    private void realizarOperacion(char operacion) {
        String str1 = etNumero1.getText().toString().trim();
        String str2 = etNumero2.getText().toString().trim();

        // Validar que ambos campos contengan números
        if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)) {
            Toast.makeText(this, "Por favor ingrese ambos números", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(str1);
            double num2 = Double.parseDouble(str2);
            double res = 0;

            switch (operacion) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num1 - num2;
                    break;
                case '/':
                    // Evitar división por cero
                    if (num2 == 0) {
                        Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                        tvResultado.setText("Error: División por cero");
                        return;
                    }
                    res = num1 / num2;
                    break;
            }

            // Mostrar el resultado de la operación
            if (res == (long) res) {
                tvResultado.setText(String.format(Locale.getDefault(), "Resultado: %d", (long) res));
            } else {
                tvResultado.setText(String.format(Locale.getDefault(), "Resultado: %.2f", res));
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show();
        }
    }
}