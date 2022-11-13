package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button nextB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextB = findViewById(R.id.nextB);
        nextB.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, dept.class);
            startActivity(i);
        });

    }
}