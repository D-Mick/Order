package com.rotimijohnson.auth.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.rotimijohnson.auth.R;

import androidx.appcompat.app.AppCompatActivity;

public class AddMoneyActivity extends AppCompatActivity {

    private TextInputLayout amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        amount = findViewById(R.id.addMoney_amount);
        Button btn = findViewById(R.id.addMoney_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateAmount()){
                    Toast.makeText(AddMoneyActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddMoneyActivity.this, "All good", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateAmount(){
        String valAmount = amount.getEditText().getText().toString().trim();

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