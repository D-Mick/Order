package com.rotimijohnson.auth.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rotimijohnson.auth.R;

import androidx.appcompat.app.AppCompatActivity;

public class BarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
    }

    public void cancelBtn(View view){
        String _location = getIntent().getStringExtra("location");
        String _destination = getIntent().getStringExtra("destination");
        String _seat = getIntent().getStringExtra("seat");
        Intent intent = new Intent(BarcodeActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}