package com.rotimijohnson.auth.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.rotimijohnson.auth.R;
import com.rotimijohnson.auth.model.User;
import com.rotimijohnson.auth.util.SessionManagement;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout loginEmail, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail = findViewById(R.id.email_username);
        loginPassword = findViewById(R.id.login_password);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    private boolean validateEmail() {
        String val = loginEmail.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            loginEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            loginEmail.setError("Invalid email address");
            return false;
        } else {
            loginEmail.setError(null);
            loginEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = loginPassword.getEditText().getText().toString().trim();
        String passwordval = "^(?:(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*)[^\\s]{8,}$";

        if (val.isEmpty()) {
            loginPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordval)) {
            loginPassword.setError("Password must contain at least 8 letters, one lowercase, one uppercase, one number, one special character");
            return false;
        } else {
            loginPassword.setError(null);
            loginPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        int userID = sessionManagement.getSession();
        if (userID != -1){
            //user id logged in and so move to mainActivity
            moveToMainActivity();
        }else {
            //do nothing
        }
    }

    private void moveToMainActivity(){
        String _loginEmail = loginEmail.getEditText().getText().toString().trim();
        String _loginPassword =loginPassword.getEditText().getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("email", _loginEmail);
        intent.putExtra("password", _loginPassword);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void nextScreen(View view) {
        if (!validateEmail() | !validatePassword()){
            return;
        }

        User user = new User(12, "rotimi", "rotimi@gmail.com");
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        sessionManagement.saveSession(user);
        moveToMainActivity();
    }

    public void Second_NextScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
}