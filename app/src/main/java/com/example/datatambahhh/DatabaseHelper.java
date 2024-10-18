package com.example.datatambahhh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "datatambah.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "data_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_TELEPON = "telepon";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAMA + " TEXT, " +
                COLUMN_TELEPON + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String nama, String telepon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, nama);
        values.put(COLUMN_TELEPON, telepon);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<DataModel> getAllData() {
        List<DataModel> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String nama = cursor.getString(cursor.getColumnIndex(COLUMN_NAMA));
                String telepon = cursor.getString(cursor.getColumnIndex(COLUMN_TELEPON));
                dataList.add(new DataModel(id, nama, telepon)); // Pastikan DataModel di sini diimpor
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }

    public void updateData(int id, String nama, String telepon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, nama);
        values.put(COLUMN_TELEPON, telepon);
        db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
