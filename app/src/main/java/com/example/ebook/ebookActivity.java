package com.example.ebook;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ebookActivity extends AppCompatActivity {
    String textFromMainActivity = csepdfs.nameVariable;
    private RecyclerView ebookRecycler;
    private DatabaseReference reference;
    private List<EbookData> list;
    private EbookAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        ebookRecycler = findViewById(R.id.ebookRecycler);
//


        if(textFromMainActivity=="1") {
            reference = FirebaseDatabase.getInstance().getReference().child("dbms");
        }
        else if(textFromMainActivity=="2") {
            reference = FirebaseDatabase.getInstance().getReference().child("pdf");
        }
        else if(textFromMainActivity=="3") {
            reference = FirebaseDatabase.getInstance().getReference().child("dbms");
        }
        getData();

    }

    private void getData() {
        System.out.println("**********************"+csepdfs.nameVariable);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                System.out.println(snapshot.toString()+"   Siddhu");
                list = new ArrayList<>();
                for(DataSnapshot snapshot1: snapshot.getChildren())
                {
                    EbookData data = snapshot1.getValue(EbookData.class);
                    list.add(data);
                    System.out.println(data.getUrl());
                }
                Log.e("size", String.valueOf(list.size()));
                adapter = new EbookAdapter(ebookActivity.this,list);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(ebookActivity.this));
                ebookRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ebookActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}