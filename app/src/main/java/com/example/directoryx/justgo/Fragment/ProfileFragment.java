package com.example.directoryx.justgo.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.directoryx.justgo.Adapter.ListChatAdapter;
import com.example.directoryx.justgo.Adapter.ListUserAdapter;
import com.example.directoryx.justgo.Adapter.UserAdapter;
import com.example.directoryx.justgo.Model.Chat;
import com.example.directoryx.justgo.Model.ChatList;
import com.example.directoryx.justgo.Model.Users;
import com.example.directoryx.justgo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Users> mUsers;

    FirebaseUser fuser;
    DatabaseReference reference;

    ListChatAdapter userAdapter;

    private List<ChatList> userLists;

    //private StoreAdapter mAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        //userAdapter = new ListChatAdapter(getContext(),mUsers);
        //recyclerView.setAdapter(userAdapter);






        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        fuser = FirebaseAuth.getInstance().getCurrentUser();


        userLists = new ArrayList<ChatList>();

        reference = FirebaseDatabase.getInstance().getReference("chatslist").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userLists.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ChatList chatList = snapshot.getValue(ChatList.class);
                    userLists.add(chatList);
                    Log.d("chatabcd",chatList.getId());
                }
                chatList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    private void chatList() {
        mUsers = new ArrayList<Users>();
        reference = FirebaseDatabase.getInstance().getReference("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Users users = snapshot.getValue(Users.class);
                    for (ChatList chatList : userLists){
                        if(users.getUid().equals(chatList.getId())){
                            mUsers.add(users);
                        }
                    }
                }
                Log.d("list", mUsers.toString());
                userAdapter = new ListChatAdapter(getContext(),mUsers);
                recyclerView.setAdapter(userAdapter);
                //userAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
