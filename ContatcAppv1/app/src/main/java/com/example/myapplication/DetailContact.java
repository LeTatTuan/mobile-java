package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;


public class DetailContact extends AppCompatActivity {

    private ImageButton btnCancel;
    private ImageButton btnSave;
    private ImageButton btnTakeAPhoto;
    private TextInputEditText etName;
    private TextInputEditText etPhone;
    private TextInputEditText etEmail;

    private AppDatabase appDatabase;
    private ContactDAO contactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_contact);

        btnCancel = findViewById(R.id.btn_cancel);
        btnSave = findViewById(R.id.btn_save);
        btnTakeAPhoto = findViewById(R.id.btn_take_photo);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        contactDAO = appDatabase.contactDAO();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        contactDAO.deleteAll();
                        finish();
                    }
                });
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Contact contact = new Contact(name, phone, email);
                        contactDAO.insert(contact);
                        Intent intent = new Intent();
                        intent.putExtra("isSuccess", 1);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        });
    }
}
