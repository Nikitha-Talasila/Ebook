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

        if(textFromMainActivity=="1") {
            reference = FirebaseDatabase.getInstance().getReference().child("cc");
        }
        else if(textFromMainActivity=="2") {
            reference = FirebaseDatabase.getInstance().getReference().child("cns");
        }
        else if(textFromMainActivity=="3") {
            reference = FirebaseDatabase.getInstance().getReference().child("dbms");
        }
        else if(textFromMainActivity=="4") {
            reference = FirebaseDatabase.getInstance().getReference().child("ai");
        }
        else if(textFromMainActivity=="5") {
            reference = FirebaseDatabase.getInstance().getReference().child("edc");
            System.out.println("Hello edc");
        }
        else if(textFromMainActivity=="6") {
            reference = FirebaseDatabase.getInstance().getReference().child("ss");
            System.out.println("Hello ss");
        }
        else if(textFromMainActivity=="7") {
            reference = FirebaseDatabase.getInstance().getReference().child("mpc");
            System.out.println("Hello mpc");
        }
//        else if(textFromMainActivity=="8") {
//            reference = FirebaseDatabase.getInstance().getReference().child("msm");
//        }
//        else if(textFromMainActivity=="9") {
//            reference = FirebaseDatabase.getInstance().getReference().child("sm");
//        }
//        else if(textFromMainActivity=="10") {
//            reference = FirebaseDatabase.getInstance().getReference().child("at");
//        }
//        else if(textFromMainActivity=="11") {
//            reference = FirebaseDatabase.getInstance().getReference().child("fm");
//        }
//        else if(textFromMainActivity=="12") {
//            reference = FirebaseDatabase.getInstance().getReference().child("ecp");
//        }
//        else if(textFromMainActivity=="13") {
//            reference = FirebaseDatabase.getInstance().getReference().child("drcs");
//        }
//        else if(textFromMainActivity=="14") {
//            reference = FirebaseDatabase.getInstance().getReference().child("bc");
//        }
//        else if(textFromMainActivity=="15") {
//            reference = FirebaseDatabase.getInstance().getReference().child("mb");
//        }
//        else if(textFromMainActivity=="16") {
//            reference = FirebaseDatabase.getInstance().getReference().child("oc");
//        }

        getData();

    }

    private void getData() {
//        System.out.println("**********************"+csepdfs.nameVariable);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

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