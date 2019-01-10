package com.example.caspian.taskmanager;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caspian.taskmanager.Adapter.TaskAdapter;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragmentAll extends Fragment {
    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;
    private TaskLab mTaskLab;
    private List<Task> mTaskList;



    public static ListFragmentAll newInstance() {

        Bundle args = new Bundle();

        ListFragmentAll fragment = new ListFragmentAll();
        fragment.setArguments(args);
        return fragment;
    }

    public ListFragmentAll() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskLab = TaskLab.getmInstance();
        mTaskList = mTaskLab.getTaskList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_all, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_all);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (mTaskAdapter == null)
           mTaskAdapter = new TaskAdapter(mTaskList,getActivity());
        mRecyclerView.setAdapter(mTaskAdapter);

        return view;
    }

    @Override
    public void onResume() {
        Log.i("Res", "onResume: All");
        super.onResume();
        if (mTaskAdapter == null)
            mTaskAdapter = new TaskAdapter(mTaskList,getActivity());
        mRecyclerView.setAdapter(mTaskAdapter);
    }
}
