package com.example.beathunger1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.beathunger1.adapter.DeviceFragmentPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Swipetab extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipetab);
        toolbar = findViewById((R.id.toolbar_main));
        tabLayout = findViewById(R.id.tabs_main);
        viewPager = findViewById(R.id.view_pager_main);

        setSupportActionBar(toolbar);
        setupViewPager();
    }
        private void setupViewPager() {
            DeviceFragmentPagerAdapter adapter = new DeviceFragmentPagerAdapter(getSupportFragmentManager());
            adapter.addFrag(new DescriptionFragment(), "DESCRIPTION");
            adapter.addFrag(new ContactFragment(), "CONTACTS");
            adapter.addFrag(new LocationFragment(), "LOCATION");

            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);

        }


}