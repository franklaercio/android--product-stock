package br.ufrn.edu.market.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrn.edu.market.R;
import br.ufrn.edu.market.datasource.DatabaseHelper;

public class UpdateProductActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private EditText codeEditText;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText quantityEditText;

    boolean hasErrors = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        databaseHelper = new DatabaseHelper(this);

        codeEditText = findViewById(R.id.editTextCode);
        nameEditText = findViewById(R.id.editTextName);
        descriptionEditText = findViewById(R.id.editTextDescription);
        quantityEditText = findViewById(R.id.editTextQuantity);

        findViewById(R.id.btnUpdate).setOnClickListener(v -> validate());
    }

    private void validate() {
        String code = codeEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String quantity = quantityEditText.getText().toString().trim();

        hasErrors = false;

        if (TextUtils.isEmpty(code)) {
            codeEditText.setError("Campo obrigatório");
            changeState();
        }

        saveProduct(code, name, description, quantity);
    }

    private void saveProduct(String code, String name, String description, String quantity) {
        if(!hasErrors) {
            try {
                boolean isUpdated = databaseHelper.update(code, name, description, quantity);

                if(isUpdated) {
                    Toast.makeText(this, "Produto atualizado com sucesso!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Verifique o código!", Toast.LENGTH_LONG).show();
                }
            } catch (Exception ex) {
                Toast.makeText(this, "Não foi possível atualizar o produto", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void changeState() {
        if(!hasErrors) {
            hasErrors = true;
        }
    }
}