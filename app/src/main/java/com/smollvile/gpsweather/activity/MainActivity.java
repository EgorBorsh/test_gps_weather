package com.smollvile.gpsweather.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.smollvile.gpsweather.R;
import com.smollvile.gpsweather.adapter.TabAdapter;
import com.smollvile.gpsweather.fragment.FragmentMain;
import com.smollvile.gpsweather.fragment.FragmentStory;

public class MainActivity extends AppCompatActivity {

    TabLayout tabBar;
    ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        //методы инициализации
        initPager();
        initTabBar();
    }

    private void initPager() {
        pager = (ViewPager) findViewById(R.id.pager);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentMain(this), "текущее");
        adapter.addFragment(new FragmentStory(this), "история");

        pager.setAdapter(adapter);

    }

    private void initTabBar() {

        tabBar = (TabLayout) findViewById(R.id.tabBar);

        tabBar.setupWithViewPager(pager);
        tabBar.getTabAt(0).select();
    }
}
