package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private ArrayList<Contact> contactList;
    private  ContactAdapter contactAdapter;
    private FloatingActionButton btnAdd;
    private ImageButton btnSearch;
    private ImageButton btnBack;
    private TextInputLayout searchTextInputLayout;
    private TextInputEditText searchTextInputEditText;
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
        btnSearch = findViewById(R.id.btn_search);
        searchTextInputLayout = findViewById(R.id.searchTextInputLayout);
        searchTextInputEditText = findViewById(R.id.searchTextInputEditText);
        btnBack = findViewById(R.id.btn_back);
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        contactDAO = appDatabase.contactDAO();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                contactList.addAll(contactDAO.getAll());
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTextInputLayout.setVisibility(View.VISIBLE);
                searchTextInputEditText.requestFocus();

                MaterialToolbar toolbar = findViewById(R.id.toolbar);
                toolbar.setVisibility(View.GONE);

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        contactList.clear();
                        contactAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTextInputLayout.setVisibility(View.GONE);
                searchTextInputEditText.setText("");

                MaterialToolbar toolbar = findViewById(R.id.toolbar);
                toolbar.setVisibility(View.VISIBLE);

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        contactList.clear();
                        contactList.addAll(contactDAO.getAll());
                        contactAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        searchTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString().trim();

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<Contact> filteredContacts = contactDAO.searchContacts(keyword);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                contactList.clear();
                                contactList.addAll(filteredContacts);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        contactAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
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
