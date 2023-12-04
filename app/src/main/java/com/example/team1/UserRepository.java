package com.example.team1;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    private ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public UserRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    // Ensure that the login method returns LiveData<User>
    public LiveData<User> login(String username, String password) {
        // Implement your login logic and return LiveData<User>
        // For example:
         return userDao.login(username, password);
        // Make sure to handle the database operation in the background thread
    }

    public void insert(User user) {
        databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    // Add other methods as needed
}
