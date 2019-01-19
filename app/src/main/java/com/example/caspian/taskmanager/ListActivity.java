package com.example.caspian.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.caspian.taskmanager.model.Account;
import com.example.caspian.taskmanager.model.AccountLab;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;
import java.util.UUID;

public class ListActivity extends AppCompatActivity {
    public static final String ACCOUNTID = "com.example.caspian.taskmanager.accId";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFloatingActionButton;
    private List<Task> mTaskList;
    private AccountLab mAccountLab;


    public static Intent newIntent(Context context, UUID accountId ){
        Intent intent = new Intent(context,ListActivity.class);
        intent.putExtra(ACCOUNTID, accountId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mTaskList = TaskLab.getmInstance(this).getTaskList();




        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TaskActivity.newIntent(ListActivity.this, null));

            }
        });
        mTabLayout = findViewById(R.id.tab_layout);

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (mTaskList.size() == 0)
                    return new TaskleesFragment();
                else {
                    if (position == 0)
                        return ListFragmentAll.newInstance(position);
                    else
                        return ListFragmentAll.newInstance(position);
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "All";
                    case 1:
                        return "Done";
                    default:
                        return "";
                }
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(">>/", "onResume: Activity");
        mTaskList = TaskLab.getmInstance(this).getTaskList();
        Log.i("activitylist", "onResume: " + mTaskList.size());
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (mTaskList.size() == 0)
                    return new TaskleesFragment();
                else {
                    if (position == 0)
                        return ListFragmentAll.newInstance(position);
                    else
                        return ListFragmentAll.newInstance(position);
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "All";
                    case 1:
                        return "Done";
                    default:
                        return "";
                }
            }
        });
        }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("vasaaaa", "onDestroy: "+Account.isIsGUess());
        if (Account.isIsGUess()){
            mAccountLab.getInstance(this).removeAccount((UUID) getIntent().getSerializableExtra(ACCOUNTID));
        }
    }
}
