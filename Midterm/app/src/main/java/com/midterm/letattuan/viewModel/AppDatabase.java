package com.midterm.letattuan.viewModel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.midterm.letattuan.model.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDAO questionDAO();

    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            AppDatabase db = Room.databaseBuilder(context,
                    AppDatabase.class, "questions").build();
            return db;
        }
        return instance;
    }
}