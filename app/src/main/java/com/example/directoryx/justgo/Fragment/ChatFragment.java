package com.example.directoryx.justgo.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.directoryx.justgo.Adapter.MessageAdapter;
import com.example.directoryx.justgo.Model.Chat;
import com.example.directoryx.justgo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatFragment extends Fragment {


    public Button btnchat,btnhangout;
    LinearLayout layout;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    DatabaseReference reference1, reference2;
    FirebaseUser fuser;
    MessageAdapter messageAdapter;
    List<Chat> mChat;
    RecyclerView recyclerView;



    //private StoreAdapter mAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_chat, container, false);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        //final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        reference1 = FirebaseDatabase.getInstance().getReference("users").child(fuser.getUid());

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        readMessage(fuser.getUid(),"g8q0d4E96eZk4z9m9fBjJ4eSmvk2","default");




        //layout = (LinearLayout) view.findViewById(R.id.layout1);
        sendButton = (ImageView)view.findViewById(R.id.sendButton);
        messageArea = (EditText)view.findViewById(R.id.messageArea);
        scrollView = (ScrollView)view.findViewById(R.id.scrollView);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receiver = "g8q0d4E96eZk4z9m9fBjJ4eSmvk2";
                String messageText = messageArea.getText().toString();
                if(!messageText.equals("")){
                    sendMessage(fuser.getUid(),receiver,messageText);
                    messageArea.setText("");
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });



        //FirebaseDatabase.setAndroidContext(this);


        //recyclerView = view.findViewById(R.id.recycler_view);
        //movieList = new ArrayList<>();
        //mAdapter = new StoreAdapter(getActivity(), movieList);

        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        //recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(mAdapter);
        //recyclerView.setNestedScrollingEnabled(false);

        //fetchStoreItems();

        return view;
    }

    private void sendMessage(String sender,String receiver,String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);

        reference.child("chats").push().setValue(hashMap);


        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("chatslist")
                .child(fuser.getUid())
                .child("g8q0d4E96eZk4z9m9fBjJ4eSmvk2");

        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    chatRef.child("id").setValue("g8q0d4E96eZk4z9m9fBjJ4eSmvk2");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void readMessage(final String myid, final String userid, final String imageurl){
        mChat = new ArrayList<>();

        reference1 =  FirebaseDatabase.getInstance().getReference("chats");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if(chat.getReceiver().equals(myid) && chat.getSender().equals(userid) || chat.getReceiver().equals(userid) && chat.getSender().equals(myid) ){
                        mChat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(getContext(),mChat);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

