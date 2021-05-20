package com.example.lastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lastapp.ui.login.LoginActivity;

public class EntryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        final Button loginButton = findViewById(R.id.login_btn);
        final Button registerButton = findViewById(R.id.register_btn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity(v);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity(v);
            }
        });
    }

    public void openLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openRegisterActivity(View view) {
        Intent intent = new Intent(this, PreRegistActivity.class);
        startActivity(intent);
    }
}