package com.example.team1.ui.login;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team1.R;

public class LoginFragment extends Fragment {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_login, container, false);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvRegister = view.findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Check if the username and password are correct
                if (isValidCredentials(username, password)) {
                    // Credentials are valid, open the app or navigate to the home screen
                    // For simplicity, show a toast message here
                    Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();

                    // TODO: Navigate to the home screen or perform the required action
                } else {
                    // Invalid credentials, show an error message
                    Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle register text click
                // Navigate to the registration screen
                // Use FragmentManager to replace this fragment with the RegisterFragment
            }
        });

        return view;
    }

    // Dummy method to check if credentials are valid
    private boolean isValidCredentials(String username, String password) {
        // Replace this with your actual authentication logic
        return username.equals("emilroy") && password.equals("password");
    }
}
