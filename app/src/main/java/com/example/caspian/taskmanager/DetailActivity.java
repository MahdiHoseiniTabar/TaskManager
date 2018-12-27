package com.example.caspian.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class DetailActivity extends AppCompatActivity {
    public static final String ID = "com.example.caspian.taskmanager.id";

    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id",id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
