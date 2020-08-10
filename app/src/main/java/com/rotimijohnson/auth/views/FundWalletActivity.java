package com.rotimijohnson.auth.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.rotimijohnson.auth.R;

import androidx.appcompat.app.AppCompatActivity;

public class FundWalletActivity extends AppCompatActivity {

    private LinearLayout add_money,transfer,withdraw,balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_wallet);

        add_money = findViewById(R.id.add_money);
        transfer = findViewById(R.id.transfer);
        withdraw = findViewById(R.id.withdrawal);
        balance = findViewById(R.id.balance);

        add_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(FundWalletActivity.this, AddMoneyActivity.class);
                startActivity(intent);
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(FundWalletActivity.this, TransferActivity.class);
                startActivity(intent);
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(FundWalletActivity.this, WithdrawActivity.class);
                startActivity(intent);
            }
        });

        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(FundWalletActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });

    }
}