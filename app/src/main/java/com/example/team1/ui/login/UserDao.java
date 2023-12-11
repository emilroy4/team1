package com.example.team1.ui.login;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.team1.ui.login.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    LiveData<User> login(String username, String password);

    // Add other queries as needed
    @Query("SELECT * FROM user WHERE userId = :userId")
    LiveData<User> getUserById(int userId);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();
}
