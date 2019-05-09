package com.example.helloword;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainContainerActivity extends AppCompatActivity {
    public TabLayout tab;
    public TabItem network;
    public TabItem profile;
    public TabItem gallery;
    public ViewPager container;
    public PageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        this.tab = findViewById(R.id.tab);
        this.network = findViewById(R.id.network);
        this.profile = findViewById(R.id.profile);
        this.gallery = findViewById(R.id.gallery);
        this.container = findViewById(R.id.container);
        this.pageAdapter = new PageAdapter(getSupportFragmentManager(), tab.getTabCount());
        container.setAdapter(pageAdapter);
        container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));


    }
}
