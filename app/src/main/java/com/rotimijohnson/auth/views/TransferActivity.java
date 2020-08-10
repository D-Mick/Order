package com.rotimijohnson.auth.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.rotimijohnson.auth.R;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class TransferActivity extends AppCompatActivity {

    private TextInputLayout recipient;
    private TextInputLayout amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        recipient = findViewById(R.id.recipient);
        amount = findViewById(R.id.amount);
        Button btn = findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateRecipient() | !validateAmount()){
                    Toast.makeText(TransferActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TransferActivity.this, "All good", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateRecipient(){
        String valrecipient = Objects.requireNonNull(recipient.getEditText()).getText().toString().trim();

        if (valrecipient.isEmpty()){
            recipient.setError("Field cannot be empty");
            return false;
        }
        else {
            recipient.setError(null);
            recipient.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateAmount(){
        String valAmount = Objects.requireNonNull(amount.getEditText()).getText().toString().trim();

        if (valAmount.isEmpty()){
            amount.setError("Field cannot be empty");
            return false;
        }
        else {
            amount.setError(null);
            amount.setErrorEnabled(false);
            return true;
        }
    }
}