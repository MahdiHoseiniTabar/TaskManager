package com.example.caspian.taskmanager;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class DetailActivity extends AppCompatActivity {
    public static final String ID = "com.example.caspian.taskmanager.id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.detail_fragment_container, DialogFragmentEdit.newInstance((UUID) getIntent().getSerializableExtra(ID)))
                .commit();
    }
}
