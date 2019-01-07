package com.example.directoryx.justgo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.directoryx.justgo.Fragment.MainFragment;
import com.example.directoryx.justgo.Fragment.ProfileFragment;

public class DashboardActivity extends AppCompatActivity {

    private Button chatucup;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = getSupportActionBar();

        loadFragment(new MainFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(2).setChecked(true);


        //chatucup = (Button) findViewById(R.id.chatucup);

        //chatucup.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent detail = new Intent(DashboardActivity.this,DetailChatActivity.class);
         //       startActivity(detail);
         //   }
        //});
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_profile:
//                    toolbar.setTitle("Shop");
                    return true;
                case R.id.navigation_message:
//                    toolbar.setTitle("My Gifts");
                    loadFragment(new ProfileFragment());
                    //Toast.makeText(DashboardActivity.this, "Chat", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_main:
                    loadFragment(new MainFragment());
//                    toolbar.setTitle("Cart");
                    return true;
                case R.id.navigation_notification:
//                    toolbar.setTitle("Profile");
                    return true;
                case R.id.navigation_setting:
//                    toolbar.setTitle("Profile");
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
