package com.example.directoryx.justgo.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.directoryx.justgo.Model.Users;
import com.example.directoryx.justgo.R;

import org.w3c.dom.Text;

import java.util.List;

public class ListChatAdapter extends RecyclerView.Adapter<ListChatAdapter.ViewHolder> {
    private Context mContext;
    private List<Users> mUsers;


    public ListChatAdapter(Context mContext,List<Users> mUsers){
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_chat,viewGroup,false);
        return new ListChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Users user = mUsers.get(i);
        viewHolder.nama.setText(user.getNama());
        //if (user.getUrlphoto().equals("default")){
        viewHolder.profile_image.setImageResource(R.drawable.user);
        //} else {
        //    Glide.with(mContext).load(user.getUrlphoto()).into(viewHolder.profile_image);
        //}
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nama;
        public ImageView profile_image;

        public ViewHolder(View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.tvnama);
            profile_image = itemView.findViewById(R.id.imageView3);

        }
    }


}
