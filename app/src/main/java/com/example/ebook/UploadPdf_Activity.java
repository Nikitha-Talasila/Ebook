package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadPdf_Activity extends AppCompatActivity {

    FloatingActionButton logout;
    Button uploadPdf,viewPdfs;
    TextView selectPdf;
    EditText pdfTitle;
    Uri pdfUri;
    FirebaseDatabase database;
    FirebaseStorage storage;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);


        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        selectPdf = findViewById(R.id.selectPdf);
        pdfTitle = findViewById(R.id.title);
        uploadPdf = findViewById(R.id.uploadPdf);
        logout = findViewById(R.id.logout);
        viewPdfs = findViewById(R.id.ViewDept);

        selectPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(UploadPdf_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectfile();
                }
                else{
                    ActivityCompat.requestPermissions(UploadPdf_Activity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UploadPdf_Activity.this,MainActivity.class);
                startActivity(i);
            }
        });

        viewPdfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UploadPdf_Activity.this,dept.class);
                startActivity(i);
            }
        });

        uploadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pdfUri!=null)
                    uploadFile(pdfUri);
                else
                    Toast.makeText(UploadPdf_Activity.this,"Select a file",Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void uploadFile(Uri pdfUri) {

        String fileName = pdfTitle.getText().toString();
        StorageReference storageReference = storage.getReference();
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        storageReference.child("Uploads").child(fileName).putFile(pdfUri).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getStorage().getDownloadUrl().toString();
                        System.out.print(url);
                        DatabaseReference reference = database.getReference();
                        reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(UploadPdf_Activity.this,"Uploaded",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(UploadPdf_Activity.this,"Not Uploaded",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(UploadPdf_Activity.this,"Not Uploaded",Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                int currentProgress= (int) (100* snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectfile();
        }
        else
            Toast.makeText(UploadPdf_Activity.this,"please provide permission..",Toast.LENGTH_SHORT).show();

    }

    private void selectfile() {

        Intent i = new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,86);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null)
        {
            pdfUri = data.getData();
        }
        else{
            Toast.makeText(UploadPdf_Activity.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }

    }
}