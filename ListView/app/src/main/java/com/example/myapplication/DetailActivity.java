package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private EditText tvDetail;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetail = findViewById(R.id.txt_value);
        btnSave = findViewById(R.id.btn_save);
        Intent receiveIntent = getIntent();
        if(receiveIntent != null) {
            String data = receiveIntent.getStringExtra("number");
            tvDetail.setText(data);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newData = tvDetail.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("editNumber", newData);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}