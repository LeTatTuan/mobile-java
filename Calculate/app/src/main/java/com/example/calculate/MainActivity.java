package com.example.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtA;
    private EditText txtB;
    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;
    private ListView CalculationHistory;
    private List<String> result;
    private  ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        CalculationHistory = findViewById(R.id.CalculationHistory);

        result = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                result
        );

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button)v;
                onClickButton(button.getText().toString());
            }
        };

        btnAdd.setOnClickListener(onClickListener);
        btnSub.setOnClickListener(onClickListener);
        btnMul.setOnClickListener(onClickListener);
        btnDiv.setOnClickListener(onClickListener);

    }

    private void onClickButton(String operator) {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        switch (operator) {
            case "+": this.result.add(String.format(" %d + %d = %d", a, b, a+b)); break;
            case "-": this.result.add(String.format(" %d - %d = %d", a, b, a-b)); break;
            case "*": this.result.add(String.format(" %d * %d = %d", a, b, a*b)); break;
            case "/": this.result.add(String.format(" %d / %d = %.2f", a, b, (double)a/b)); break;
        }
        this.CalculationHistory.setAdapter(this.adapter);
    }
}