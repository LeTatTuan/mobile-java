package com.midterm.letattuan.viewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.letattuan.R;
import com.midterm.letattuan.model.Question;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private ArrayList<Question> QuestionList;

    public QuestionAdapter(ArrayList<Question> QuestionList) {
        this.QuestionList = QuestionList;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        Question question = QuestionList.get(position);
        holder.tvQuetion.setText(question.getQuestion());
        holder.tvResult.setText(question.getResult());
    }

    @Override
    public int getItemCount() {
        return QuestionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvQuetion;
        public TextView tvResult;
        public ViewHolder(@NonNull View view) {
            super(view);
            tvQuetion = (TextView) view.findViewById(R.id.tv_question1);
            tvResult = (TextView) view.findViewById(R.id.tv_result);
        }
    }
}
