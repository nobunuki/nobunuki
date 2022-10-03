package com.example.summareconsensor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {
    String data1[];
    int images[];
    Context context;
    public adapter(Context ct,String s1[], int img[]){
        context = ct;
        data1 = s1;
        images = img;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.title.setText(data1[position]);
        holder.imageView.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            imageView = itemView.findViewById(R.id.myimgview);

        }
    }
}
