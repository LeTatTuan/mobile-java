package com.midterm.letattuan.viewModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.midterm.letattuan.model.Question;
import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM Question")
    List<Question> getAll();
    @Insert
    void insert(Question... questions) ;
    @Query("DELETE FROM QUESTION WHERE id = :questionId")
    void deleteById(int questionId);

    @Update
    void update(Question question);

}
