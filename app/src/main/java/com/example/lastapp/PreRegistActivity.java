package com.example.lastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lastapp.ui.login.LoginActivity;
import com.example.lastapp.ui.register.RegisterActivity;

public class PreRegistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_regist);

        final Button userButton = findViewById(R.id.user_btn);
        final Button organizationButton = findViewById(R.id.orgs_btn);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserRegisterActivity(v);
            }
        });

        organizationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrganizationRegisterActivity(v);
            }
        });
    }

    public void openUserRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openOrganizationRegisterActivity(View view) {
        Intent intent = new Intent(this, PreRegistActivity.class);
        startActivity(intent);
    }
}