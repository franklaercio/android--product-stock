package br.ufrn.edu.market.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.ufrn.edu.market.domain.Product;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_DO_BANCO = "product.db";
    private static final int VERSAO_DO_BANCO = 1;

    public static final String TABLE_PRODUCTS = "product";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_QUANTITY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTable = "CREATE TABLE product (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_CODE + " TEXT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_QUANTITY + " INTEGER)";
        db.execSQL(sqlCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS product");
        onCreate(db);
    }

    public void save(String code, String name, String description, Integer quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("code", code);
        values.put("name", name);
        values.put("description", description);
        values.put("quantity", quantity);

        long newRowId = db.insert("product", null, values);
        db.close();
    }

    public List<Product> getAll() {
        List<Product> listProduct = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "product",
                new String[]{"_id", "code", "name", "description", "quantity"},
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Product produto = new Product.Builder()
                .id(cursor.getInt(cursor.getColumnIndexOrThrow("_id")))
                .code(cursor.getString(cursor.getColumnIndexOrThrow("code")))
                .name(cursor.getString(cursor.getColumnIndexOrThrow("name")))
                .description(cursor.getString(cursor.getColumnIndexOrThrow("description")))
                .quantity(cursor.getInt(cursor.getColumnIndexOrThrow("quantity")))
                .build();

            listProduct.add(produto);
        }

        cursor.close();
        db.close();

        return listProduct;
    }

    public boolean delete(String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_PRODUCTS, COLUMN_CODE + " = ?", new String[]{code});
        return result > 0;
    }

    public boolean update(String code, String newName, String newDescription, String newQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        putValueIfNotEmpty(newName, COLUMN_NAME, false, values);
        putValueIfNotEmpty(newDescription, COLUMN_DESCRIPTION, false, values);
        putValueIfNotEmpty(newQuantity, COLUMN_QUANTITY, true, values);

        int result = db.update(TABLE_PRODUCTS, values, COLUMN_CODE + " = ?", new String[]{code});
        return result > 0;
    }

    private static void putValueIfNotEmpty(String newValue, String columnName, boolean isInt, ContentValues values) {
        if(newValue != null && !newValue.isEmpty()) {
            if(isInt) {
                values.put(columnName, Integer.parseInt(newValue));
            } else {
                values.put(columnName, newValue);
            }

        }
    }
}
