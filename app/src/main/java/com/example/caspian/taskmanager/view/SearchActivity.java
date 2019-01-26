package com.example.caspian.taskmanager.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.caspian.taskmanager.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFragmentSearch dialogFragmentSearch = DialogFragmentSearch.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.container_search,dialogFragmentSearch)
                .commit();
    }
}
