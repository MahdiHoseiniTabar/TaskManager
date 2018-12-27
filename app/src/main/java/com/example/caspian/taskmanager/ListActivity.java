package com.example.caspian.taskmanager;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFloatingActionButton;
    private Task mTask;
    private TaskLab mTaskLab;
    private List<Task> mTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mTaskList = TaskLab.getmInstance().getTaskList();


        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskFragment taskFragment = new TaskFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container,taskFragment)
                        .commit();

            }
        });
        mTabLayout = findViewById(R.id.tab_layout);

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0)
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
                switch (position){
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
}
