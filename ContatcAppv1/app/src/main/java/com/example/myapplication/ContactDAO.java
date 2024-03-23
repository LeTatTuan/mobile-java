package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Insert
    void insert(Contact... contacts);

    @Query("SELECT * FROM Contact WHERE LOWER(name) LIKE '%' || LOWER(:keyword) || '%'")
    List<Contact> searchContacts(String keyword);

    @Query("DELETE FROM Contact")
    void deleteAll();

    @Query("SELECT * FROM Contact WHERE id = :id")
    Contact getContactById(int id);

    @Query("DELETE FROM Contact WHERE id = :id")
    void deleteContactById(int id);

    @Query("UPDATE Contact SET name = :name, mobile = :phone, email = :email WHERE id = :id")
    void updateContact(int id, String name, String phone, String email);

}
