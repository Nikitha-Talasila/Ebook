package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    Button login;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        login = findViewById(R.id.loginBtn);
        username=findViewById(R.id.emailIt);
        password=findViewById(R.id.passwordIt);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String user = username.getText().toString().trim();
        String pass= password.getText().toString().trim();

        if(user.equals("Admin") && pass.equals("BookAdmin#1"))
        {
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(AdminActivity.this,UploadPdf_Activity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
        }
    }
}