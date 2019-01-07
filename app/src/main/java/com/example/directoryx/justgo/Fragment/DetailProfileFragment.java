package com.example.directoryx.justgo.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.directoryx.justgo.R;

public class DetailProfileFragment extends Fragment {


    public Button btnchat,btnhangout;


    //private StoreAdapter mAdapter;

    public DetailProfileFragment() {
        // Required empty public constructor
    }

    public static DetailProfileFragment newInstance(String param1, String param2) {
        DetailProfileFragment fragment = new DetailProfileFragment();
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
        View view = inflater.inflate(R.layout.activity_detail_chat, container, false);
        btnchat = (Button) view.findViewById(R.id.button2);
        btnhangout = (Button) view.findViewById(R.id.button);

        btnhangout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "hangout", Toast.LENGTH_SHORT).show();
            }
        });

        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, new ChatFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                //Toast.makeText(getActivity(), "chat", Toast.LENGTH_SHORT).show();
            }
        });

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

}

