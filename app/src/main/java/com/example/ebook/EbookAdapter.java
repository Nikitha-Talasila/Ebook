package com.example.ebook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder>{
    @NonNull

    private Context context;
    private List<EbookData> list;

    public EbookAdapter(@NonNull Context context, List<EbookData> list) {
        this.context = context;
        this.list = list;
    }


    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout,parent,false);
        return new EbookViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.ebookName.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PdfViewerActivity.class);
                intent.putExtra("pdfUrl",list.get(position).getUrl());
                System.out.println(list.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {

        private TextView ebookName;
        private ImageView ebookDownload;

        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);

            ebookDownload = itemView.findViewById(R.id.ebookDownload);
            ebookName=itemView.findViewById(R.id.ebookName);
        }
    }
}
