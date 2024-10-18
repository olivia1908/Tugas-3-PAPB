package com.example.datatambahhh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNama;
    private EditText editTextTelepon;
    private Button buttonTambah;
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Pastikan layout ini sesuai

        // Inisialisasi UI
        editTextNama = findViewById(R.id.namaEditText);
        editTextTelepon = findViewById(R.id.teleponEditText);
        buttonTambah = findViewById(R.id.tambahButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Inisialisasi DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Mengatur RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();

        // Set click listener untuk tombol tambah
        buttonTambah.setOnClickListener(v -> {
            String nama = editTextNama.getText().toString().trim();
            String telepon = editTextTelepon.getText().toString().trim();

            if (!nama.isEmpty() && !telepon.isEmpty()) {
                databaseHelper.insertData(nama, telepon);
                loadData();
                editTextNama.setText("");
                editTextTelepon.setText("");
                Toast.makeText(MainActivity.this, "Data ditambahkan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Silakan isi semua kolom", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        List<DataModel> dataList = databaseHelper.getAllData();
        dataAdapter = new DataAdapter(dataList);
        recyclerView.setAdapter(dataAdapter);
    }
}
