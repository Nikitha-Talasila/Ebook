package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class dept extends AppCompatActivity {
    private ListView mListView;
    private String[] names = {"CSE","ECE","IT","MECH","BT","FT"};
    private int [] images = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);
        mListView = findViewById(R.id.listview);

        MyAdapter adapter = new MyAdapter();
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent in = new Intent(view.getContext(),csepdfs.class);
                    startActivity(in);
                }
                else if(i==1){
                    Intent in = new Intent(view.getContext(),ecepdfs.class);
                    startActivity(in);
                }
                else{
                    Intent in = new Intent(view.getContext(),itpdfs.class);
                    startActivity(in);
                }
            }
        });
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.card, parent ,false);
            ImageView mImageView = convertView.findViewById(R.id.imageview);
            TextView mTextView = convertView.findViewById(R.id.textview);

            mTextView.setText(names[i]);
            mImageView.setImageResource(images[i]);


            return convertView;
        }


    }

//    CardView cse;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dept);
//        cse = findViewById(R.id.cardview);
//        cse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i1 = new Intent(dept.this, MainActivity.class);
//                startActivity(i1);
//            }
//        });
//
//    }
}