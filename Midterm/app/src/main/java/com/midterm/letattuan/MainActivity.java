package com.midterm.letattuan;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.midterm.letattuan.model.Question;
import com.midterm.letattuan.viewModel.AppDatabase;
import com.midterm.letattuan.viewModel.QuestionAdapter;
import com.midterm.letattuan.viewModel.QuestionDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private Button btnTrue;
    private Button btnFalse;
    private FloatingActionButton btnPrevious;
    private FloatingActionButton btnNext;
    private Button btnSubmit;
    private ArrayList<Question> questionList;
    private QuestionAdapter questionAdapter;
    private AppDatabase appDatabase;
    private QuestionDAO questionDAO;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tv_question);
        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        btnPrevious = findViewById(R.id.btn_previous);
        btnNext = findViewById(R.id.btn_next);
        questionList = new ArrayList<>();
        questionAdapter = new QuestionAdapter(questionList);
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        questionDAO = appDatabase.questionDAO();
        position = 0;



        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                questionList.addAll((ArrayList<Question>) questionDAO.getAll());
                showCurrentQuestion();
            }
        });
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                questionDAO.insert(new Question("Em an com chua?", "no"));
//                questionDAO.insert(new Question("Ban dang hoc tai bkdn dung khong?", "yes"));
//                questionDAO.insert(new Question(" Canbera is the captial of Australia", "yes"));
//                questionDAO.insert(new Question(" Viet Nam la mot nuoc o khu vuc Dong Nam A", "yes"));
//            }
//        });


        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToPreviousQuestion();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNextQuestion();
            }
        });
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void showCurrentQuestion() {
        tvQuestion.setText(questionList.get(position).getQuestion());
        btnTrue.setBackgroundColor(Color.LTGRAY);
        btnFalse.setBackgroundColor(Color.LTGRAY);
    }
    private void moveToNextQuestion() {
        if (position < questionList.size() - 1) {
            position++;
            showCurrentQuestion();
        }
    }
    private void moveToPreviousQuestion() {
        if (position > 0) {
            position--;
            showCurrentQuestion();
        }
   }
}