package com.example.contactappv2.viewModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contactappv2.model.Contact;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Insert
    void insert(Contact... contacts); //Có thể truyền 1 hoặc nhiều contact

    @Query("DELETE FROM Contact WHERE id = :contactId")
    void deleteById(int contactId);

    @Update
    void update(Contact contact);

    @Query("DELETE FROM Contact")
    void deleteAll();
}
