package com.example.sakib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RechargeActivity extends AppCompatActivity {


    Button buttonRecharge;
    EditText rechargeAmountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        buttonRecharge = (Button) findViewById(R.id.button_recharge_submit);
        rechargeAmountEditText = (EditText) findViewById(R.id.rechargeAmountText);

        buttonRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RechargeActivity.this, PaymentActivity.class);
                intent.putExtra("amount",rechargeAmountEditText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
