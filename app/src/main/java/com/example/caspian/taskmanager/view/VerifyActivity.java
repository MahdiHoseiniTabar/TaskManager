package com.example.caspian.taskmanager.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.caspian.taskmanager.R;
import com.example.caspian.taskmanager.model.Account;

import java.util.UUID;

public class VerifyActivity extends AppCompatActivity {
    public static final String ID = "com.example.caspian.taskmanager.id";

    public static Intent newIntent(Context context , UUID id) {
        Intent intent = new Intent(context, VerifyActivity.class);
        intent.putExtra(ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        Log.i("back<", "onCreate: verify " + Account.isIsGUess());
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (Account.isIsGUess())
            fragmentManager.beginTransaction()
                    .replace(R.id.verify_container, SignUpFragment.newInstance((UUID) getIntent().getSerializableExtra(ID)))
                    .commit();
        else
            fragmentManager.beginTransaction()
                    .replace(R.id.verify_container, new SignInFragment())
                    .commit();
    }
}
