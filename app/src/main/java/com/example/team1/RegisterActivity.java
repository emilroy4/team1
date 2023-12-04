package com.example.team1;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

// RegisterActivity.java
public class RegisterActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        EditText etRegisterUsername = findViewById(R.id.etRegisterUsername);
        EditText etRegisterPassword = findViewById(R.id.etRegisterPassword);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvLogin = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v -> {
            String username = etRegisterUsername.getText().toString().trim();
            String password = etRegisterPassword.getText().toString().trim();

            // Perform user registration using ViewModel
            userViewModel.insert(new User(username, password));

            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();

            // Redirect to the login activity
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        tvLogin.setOnClickListener(v -> {
            // Open the login activity
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
