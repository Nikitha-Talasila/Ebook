package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ecepdfs extends AppCompatActivity {
    public static String nameVariable= "";
    private ListView mListView;
    private String[] names = {"Electronic Devices & Circuits","Signals & Systems","Microprocessors & controllers"};
    private int [] images = {R.drawable.img,R.drawable.img,R.drawable.img};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecepdfs);

        mListView = findViewById(R.id.listview);

        ecepdfs.MyAdapter adapter = new ecepdfs.MyAdapter();
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent in = new Intent(view.getContext(),ebookActivity.class);
//                        in.putExtra("key1", 1);
                    nameVariable="5";
                    startActivity(in);
                }
                else if(i==1){
                    Intent in = new Intent(view.getContext(),ebookActivity.class);
//                        in.putExtra("key2", 2);
                    nameVariable="6";
                    startActivity(in);
                }
                else if(i==2){
                    Intent in = new Intent(view.getContext(),ebookActivity.class);
//                        in.putExtra("key3", 3);
                    nameVariable="7";
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
}
