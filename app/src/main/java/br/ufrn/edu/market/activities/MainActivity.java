package br.ufrn.edu.market.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import br.ufrn.edu.market.R;
import br.ufrn.edu.market.datasource.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        SharedPreferences sharedPreferences = getSharedPreferences("app_market_preferences", Context.MODE_PRIVATE);

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
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}