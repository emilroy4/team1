package com.example.team1.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.team1.ui.login.User;
import com.example.team1.ui.login.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    // Fix the return type to LiveData<User>
    public LiveData<User> login(String username, String password) {
        return userRepository.login(username, password);
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    // Add other methods as needed
}
