package com.example.caspian.taskmanager;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class TaskActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        TaskFragment taskFragment = TaskFragment.newInstance();
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
