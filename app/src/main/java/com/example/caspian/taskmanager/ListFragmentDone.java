package com.example.caspian.taskmanager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caspian.taskmanager.Adapter.TaskAdapter;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragmentDone extends Fragment {
    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;
    private TaskLab mTaskLab;
    private List<Task> mTaskListDone;


    public ListFragmentDone() {
        // Required empty public constructor
    }

    public static ListFragmentDone newInstance() {

        Bundle args = new Bundle();

        ListFragmentDone fragment = new ListFragmentDone();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskLab = TaskLab.getmInstance(getActivity());
        mTaskListDone = mTaskLab.getDoneTaskList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_done, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_done);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (mTaskAdapter == null)
            mTaskAdapter = new TaskAdapter(mTaskListDone, getActivity());
        mRecyclerView.setAdapter(mTaskAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTaskListDone = mTaskLab.getDoneTaskList();
        mTaskAdapter = new TaskAdapter(mTaskListDone, getActivity());
        mRecyclerView.setAdapter(mTaskAdapter);
        Log.i("Res", "onResume: done");
    }
}
