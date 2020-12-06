package com.example.pasture.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pasture.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton actionButton;
    BottomNavigationView bottomNavigationView;
    OverviewFragment overviewFragment;
    ScheduleFragment scheduleFragment;
    ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        overviewFragment = new OverviewFragment();
        scheduleFragment = new ScheduleFragment();
        profileFragment = new ProfileFragment();

        getFragment(overviewFragment);
        actionButton = findViewById(R.id.fab);
        actionButton.setVisibility(View.VISIBLE);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_overview:
                        getFragment(overviewFragment);
                        actionButton.setVisibility(View.VISIBLE);
                        break;
                    case R.id.page_detail:
                        getFragment(scheduleFragment);
                        actionButton.setVisibility(View.GONE);
                        break;
                    case R.id.page_profile:
                        getFragment(profileFragment);
                        actionButton.setVisibility(View.GONE);
                        break;
                }
                return false;
            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewEventActivity.class);
                TextView overview_date = findViewById(R.id.overview_date);
                String date = overview_date.getText().toString();
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });


    }



    private void getFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }




}
