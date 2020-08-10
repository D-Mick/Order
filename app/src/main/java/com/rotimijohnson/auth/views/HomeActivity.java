package com.rotimijohnson.auth.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rotimijohnson.auth.R;
import com.rotimijohnson.auth.util.SessionManagement;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logout(View view) {
        //this method will remove session and open login screen
        SessionManagement sessionManagement = new SessionManagement(HomeActivity.this);
        sessionManagement.removeSession();

        moveToLogin();
    }

    private void moveToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}