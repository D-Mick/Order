package com.rotimijohnson.auth.views;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rotimijohnson.auth.R;

import androidx.appcompat.app.AppCompatActivity;

public class WaitingActivity extends AppCompatActivity {
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    Button yes,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
    }

    public void  closeScreen(View view){
        Intent intent = new Intent(WaitingActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void makeCall(View view) {
        builder = new AlertDialog.Builder(WaitingActivity.this);
        View v = getLayoutInflater().inflate(R.layout.dialog,null);
        yes = (Button) v.findViewById(R.id.dialog_yes);
        no = (Button) v.findViewById(R.id.dialog_no);

        builder.setView(v);
        alertDialog = builder.create();
        alertDialog.show();

        yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                String _location = getIntent().getStringExtra("location");
                String _destination = getIntent().getStringExtra("destination");
                String _seat = getIntent().getStringExtra("seat");

                String number = "09079870708";

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+number));

                intent.putExtra(_location, "location");
                intent.putExtra(_destination, "destination");
                intent.putExtra(_seat, "seat");
                startActivity(intent);
                Toast.makeText(WaitingActivity.this, "Making the call....", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WaitingActivity.this, "Please wait for the driver!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

    }
}