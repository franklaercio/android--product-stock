package br.ufrn.edu.market.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.ufrn.edu.market.R;
import br.ufrn.edu.market.datasource.DatabaseHelper;

public class CreateProductActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText codeEditText;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText quantityEditText;

    boolean hasErrors = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        databaseHelper = new DatabaseHelper(this);

        codeEditText = findViewById(R.id.editTextCode);
        nameEditText = findViewById(R.id.editTextName);
        descriptionEditText = findViewById(R.id.editTextDescription);
        quantityEditText = findViewById(R.id.editTextQuantity);
    }

    public void validate(View view) {
        String code = codeEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String quantity = quantityEditText.getText().toString().trim();

        hasErrors = false;

        if (TextUtils.isEmpty(code)) {
            codeEditText.setError("Campo obrigatório");
            changeState();
        }

        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Campo obrigatório");
            changeState();
        }

        if (TextUtils.isEmpty(description)) {
            descriptionEditText.setError("Campo obrigatório");
            changeState();
        }

        if (TextUtils.isEmpty(quantity)) {
            quantityEditText.setError("Campo obrigatório");
            changeState();
        }

        saveProduct(code, name, description, quantity);
    }

    private void saveProduct(String code, String name, String description, String quantity) {
        if(!hasErrors) {
            try {
                databaseHelper.save(code, name, description, Integer.parseInt(quantity));
                Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            } catch (Exception ex) {
                Toast.makeText(this, "Não foi possível cadastrar o produto", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void changeState() {
        if(!hasErrors) {
            hasErrors = true;
        }
    }

}