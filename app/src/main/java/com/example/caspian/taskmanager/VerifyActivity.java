package com.example.caspian.taskmanager;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VerifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        SignInFragment signInFragment = new SignInFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.signIn_fragment) == null){
            fragmentManager.beginTransaction()
                    .add(R.id.verify_container,signInFragment)
                    .commit();
        }
    }
}
