package br.ufrn.edu.market.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import br.ufrn.edu.market.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void create(View view) {
        Intent intent = new Intent(this, CreateProductActivity.class);
        startActivity(intent);
    }

    public void update(View view) {
        Intent intent = new Intent(this, UpdateProductActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent = new Intent(this, DeleteProductActivity.class);
        startActivity(intent);
    }

    public void list(View view) {
        Intent intent = new Intent(this, ListProductActivity.class);
        startActivity(intent);
    }
}
