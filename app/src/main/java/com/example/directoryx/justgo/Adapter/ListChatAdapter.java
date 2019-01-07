package com.example.directoryx.justgo.Adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.directoryx.justgo.Fragment.ChatFragment;
import com.example.directoryx.justgo.Fragment.DetailProfileFragment;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.userchat_card,viewGroup,false);
        return new ListChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Users user = mUsers.get(i);
        viewHolder.nama.setText(user.getNama());
        //if (user.getUrlphoto().equals("default")){
        viewHolder.profile_image.setImageResource(R.drawable.user);
        viewHolder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, user.getUid(), Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
                Bundle arguments = new Bundle();
                ChatFragment fragment = new ChatFragment();
                arguments.putString( "userid" , user.getUid());
                fragment.setArguments(arguments);
                transaction.replace(R.id.frame_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //} else {
        //    Glide.with(mContext).load(user.getUrlphoto()).into(viewHolder.profile_image);
        //}
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nama;
        public ImageView profile_image;
        public CardView cvMain;

        public ViewHolder(View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.title);
            profile_image = itemView.findViewById(R.id.thumbnail);
            cvMain = (CardView) itemView.findViewById(R.id.card_view);



        }
    }


}
