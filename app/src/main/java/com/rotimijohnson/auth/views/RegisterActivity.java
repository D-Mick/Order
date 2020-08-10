package com.rotimijohnson.auth.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.rotimijohnson.auth.R;
import com.rotimijohnson.auth.views.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout fullname, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    private boolean validateFullName() {
        String valfullname = fullname.getEditText().getText().toString().trim();

        if (valfullname.isEmpty()) {
            fullname.setError("Field can't be empty");
            return false;
        } else {
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName() {
        String valfullname = username.getEditText().getText().toString().trim();
        String noWhiteSpace = "\\A\\W{4,20}\\Z";

        if (valfullname.isEmpty()) {
            username.setError("Field can't be empty");
            return false;
        } else if (valfullname.length() >= 25) {
            username.setError("Full name too long");
            return false;
        } else if (valfullname.matches(noWhiteSpace)) {
            username.setError("White spaces not allowed");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String passwordval = "^(?:(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*)[^\\s]{8,}$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordval)) {
            password.setError("Password must contain at least 8 letters, one lowercase, one uppercase, one number, one special character");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void nextScreen(View view) {
        if (!validateFullName() | !validateUserName() | !validateEmail() | !validatePassword()) {
            return;
        }

        String _fullname = fullname.getEditText().getText().toString().trim();
        String _username = username.getEditText().getText().toString().trim();
        String _email = email.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("fullname", _fullname);
        intent.putExtra("username", _username);
        intent.putExtra("email", _email);
        intent.putExtra("password", _password);
        startActivity(intent);
    }

    public void Second_NextScreen_Login(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}