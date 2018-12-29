package com.example.caspian.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class TaskActivity extends AppCompatActivity {
    public static final String ID = "com.example.caspian.taskmanager.id";

    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context, TaskActivity.class);
        intent.putExtra(ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        TaskFragment taskFragment = TaskFragment.newInstance((UUID) getIntent().getSerializableExtra(ID));
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, taskFragment)
                    .commit();
        }else{
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, taskFragment)
                    .commit();
        }
    }
}
