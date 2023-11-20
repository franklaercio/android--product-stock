package br.ufrn.edu.market.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.ufrn.edu.market.R;
import br.ufrn.edu.market.domain.Product;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, List<Product> produtos) {
        super(context, 0, produtos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView txtName = convertView.findViewById(R.id.textTitle);
        TextView txtDescription = convertView.findViewById(R.id.textSubtitle);
        TextView txtQuantity = convertView.findViewById(R.id.textAmount);

        if (product != null) {
            txtName.setText(product.getName());
            txtDescription.setText(String.format("Código: %s", product.getCode()));
            txtQuantity.setText(String.format("%s unidade(s)", product.getQuantity()));
        }

        return convertView;
    }
}

