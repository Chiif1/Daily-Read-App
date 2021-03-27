package com.nugrs.newsapp_dailyread;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList ) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        String mImageURL = currentItem.getmImageURL();
        String mText1 = currentItem.getmText1();
        String mText2 = currentItem.getmText2();

        holder.mTextView1.setText(mText1);
        holder.mTextView2.setText(mText2);
        Picasso.get().load(mImageURL).fit().centerInside().into(holder.mImageview);
        holder.mRelativeLayoutURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mExampleList.get(holder.getAdapterPosition()).getmUrlToSite()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageview;
        public TextView mTextView1;
        public TextView mTextView2;
        public RelativeLayout mRelativeLayoutURL;


        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageview = itemView.findViewById(R.id.image);
            mTextView1 = itemView.findViewById(R.id.text1);
            mTextView2 = itemView.findViewById(R.id.text2);
            mRelativeLayoutURL = itemView.findViewById((R.id.exampleHokder));
        }
    }


    }
