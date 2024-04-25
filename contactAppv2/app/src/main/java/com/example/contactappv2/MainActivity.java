package com.example.contactappv2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cloudinary.Configuration;
import com.cloudinary.android.MediaManager;
import com.example.contactappv2.databinding.ActivityMainBinding;
import com.example.contactappv2.model.Contact;
import com.example.contactappv2.viewModel.AppDatabase;
import com.example.contactappv2.viewModel.ContactAdapter;
import com.example.contactappv2.viewModel.ContactDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private ArrayList<Contact> contactList;
    private ContactAdapter contactAdapter;
    private FloatingActionButton btnAdd;
    private AppDatabase appDatabase;
    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Map config = new HashMap();
        config.put("cloud_name", "" + R.string.cloudinary_cloud_name);
        config.put("secure", true);
        MediaManager.init(this, config);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactList);
        binding.rvContacts.setAdapter(contactAdapter);
        btnAdd = findViewById(R.id.btn_add);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDAO = appDatabase.contactDAO();

                List<Contact> list = contactDAO.getAll();

                for (Contact ct : list) {
                    contactList.add(ct);
                    contactAdapter.notifyDataSetChanged();
                }

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                intent.putExtra("isUpdate", true);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int result = data.getIntExtra("isSuccess", 0);
            if (result == 1) {
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        SearchView searchView;
        searchView = (SearchView) menu.findItem(R.id.mi_search).getActionView();
        assert searchView != null;
        searchView.setQueryHint("Nhập tên liên lạc");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }
}
