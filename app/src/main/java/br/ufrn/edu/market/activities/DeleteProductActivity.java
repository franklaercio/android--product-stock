package br.ufrn.edu.market.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrn.edu.market.R;
import br.ufrn.edu.market.datasource.DatabaseHelper;

public class DeleteProductActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText codeEditText;
    boolean hasErrors = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        databaseHelper = new DatabaseHelper(this);

        codeEditText = findViewById(R.id.editTextCode);

        findViewById(R.id.btnDelete).setOnClickListener(v -> validate());
    }

    private void validate() {
        String code = codeEditText.getText().toString().trim();

        hasErrors = false;

        if (TextUtils.isEmpty(code)) {
            codeEditText.setError("Campo obrigatório");
            changeState();
        }

        deleteProduct(code);
    }

    private void deleteProduct(String code) {
        if(!hasErrors) {
            try {
                boolean isDeleted = databaseHelper.delete(code);

                if(isDeleted) {
                    Toast.makeText(this, "Produto apagado com sucesso!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Verique se código está correto", Toast.LENGTH_LONG).show();
                }
            } catch (Exception ex) {
                Toast.makeText(this, "Não foi possível apagar o produto", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void changeState() {
        if(!hasErrors) {
            hasErrors = true;
        }
    }
}