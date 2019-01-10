package com.example.caspian.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.caspian.taskmanager.model.AccountLab;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFloatingActionButton;
    private ImageView image;
    private List<Task> mTaskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        AccountLab accountLab = AccountLab.getInstance(this);
        Toast.makeText(this, accountLab.getUsernameOfAccount() ,Toast.LENGTH_SHORT).show();

        mTaskList = TaskLab.getmInstance().getTaskList();

        image = findViewById(R.id.image);
        image.setImageResource(R.drawable.task);


        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TaskActivity.newIntent(ListActivity.this, null));

            }
        });
        mTabLayout = findViewById(R.id.tab_layout);

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0)
                    return ListFragmentAll.newInstance();
                else
                    return ListFragmentDone.newInstance();
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


            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                boolean flag = true;


                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    Log.i("naaaaa", "onCreate: add" );
                    if (!flag)
                        image.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (mTaskList.size() != 0)
                        flag = true;
                    else {
                        flag = false;
                        image.setVisibility(View.VISIBLE);
                    }
                }
            });

        mTabLayout.setupWithViewPager(mViewPager);

        if (mTaskList.size() != 0)
            image.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTaskList.size() != 0)
            image.setVisibility(View.INVISIBLE);
        if (mTaskList.size() == 0) {
            image.setVisibility(View.VISIBLE);
        }
    }
}
