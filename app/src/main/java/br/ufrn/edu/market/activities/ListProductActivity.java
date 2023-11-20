package br.ufrn.edu.market.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.ufrn.edu.market.R;
import br.ufrn.edu.market.adapters.ProductAdapter;
import br.ufrn.edu.market.datasource.DatabaseHelper;
import br.ufrn.edu.market.domain.Product;

public class ListProductActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        ListView listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        List<Product> listProducts = getAllProducts();
        ProductAdapter productAdapter = new ProductAdapter(this, listProducts);
        listView.setAdapter(productAdapter);
    }

    private List<Product> getAllProducts() {
        try {
            return dbHelper.getAll();
        } catch (Exception ex) {
            Toast.makeText(this, "Não foi possível recuperar os produtos", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            return null;
        }
    }
}