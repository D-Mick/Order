package com.rotimijohnson.auth.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.rotimijohnson.auth.R;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class WithdrawActivity extends AppCompatActivity {

    private TextInputLayout amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        amount = findViewById(R.id.withdraw_amount);
        Button btn = findViewById(R.id.withdraw_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateAmount()){
                    Toast.makeText(WithdrawActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(WithdrawActivity.this, "All good", Toast.LENGTH_SHORT).show();
                }
            }
        });
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