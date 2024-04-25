package com.example.contact;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private ArrayList<Contact> contactList;
    private  ContactAdapter contactAdapter;
    private FloatingActionButton btnAdd;
    private AppDatabase appDatabase;
    private ContactDAO contactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot =  binding.getRoot();
        setContentView(viewRoot);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactList);
        binding.rvContacts.setAdapter(contactAdapter);
        btnAdd = findViewById(R.id.btn_add);
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        contactDAO = appDatabase.contactDAO();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                contactList.addAll(contactDAO.getAll());
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailContact.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int result = data.getIntExtra("isSuccess", 0);
            if(result == 1) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        contactList.clear();
                        contactList.addAll(contactDAO.getAll());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                contactAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });

            }
        }
    }
}
