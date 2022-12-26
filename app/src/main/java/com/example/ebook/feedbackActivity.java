package com.example.ebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedbackActivity extends AppCompatActivity {
    CheckBox Book_Addition, App_Performance;
    Button submit,cancel;
    EditText Query,name;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Book_Addition = findViewById(R.id.booksAdd);
        App_Performance = findViewById(R.id.appPerf);
        submit=findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);
        Query = findViewById(R.id.paragraph);
        name = findViewById(R.id.name);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getbox1=null;
                String getbox2=null;
                String Name = name.getText().toString();
                String paragraph = Query.getText().toString();
                if(Book_Addition.isChecked()){
                    getbox1="True";
                }
                if(App_Performance.isChecked())
                {
                    getbox2="True";
                }
                if(Name.isEmpty() || paragraph.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
                }
                else {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("users");
                    helper helperclass = new helper(getbox1, getbox2, paragraph);
                    reference.child(Name).setValue(helperclass);
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(feedbackActivity.this,dept.class);
                startActivity(i);
            }
        });


    }
}