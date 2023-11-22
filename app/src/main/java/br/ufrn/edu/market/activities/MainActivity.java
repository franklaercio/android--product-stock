package br.ufrn.edu.market.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import br.ufrn.edu.market.R;
import br.ufrn.edu.market.datasource.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        emailEditText = findViewById(R.id.editTextLogin);
        passwordEditText = findViewById(R.id.editTextPassword);

        SharedPreferences sharedPreferences = getSharedPreferences("app_market_preferences", Context.MODE_PRIVATE);

        File arquivo = new File(this.getFilesDir(), "app_market_preferences");

        if (arquivo.exists()) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

        boolean firstTimeOpen = sharedPreferences.getBoolean("first_time_open", true);

        if (firstTimeOpen) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("first_time_open", false);
            editor.putString("login", "admin");
            editor.putString("password", "admin");
            editor.apply();
        }

        Button authenticateButton = findViewById(R.id.buttonLogin);
        authenticateButton.setOnClickListener(v -> authenticate());
    }

    public void authenticate() {

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(email)) {
            if (email.equals("admin") && password.equals("admin")) {
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Não foi possível autenticar!", Toast.LENGTH_LONG).show();
            }
        } else {
            emailEditText.setError("Campo obrigatório");
            passwordEditText.setError("Campo obrigatório");
        }
    }
}