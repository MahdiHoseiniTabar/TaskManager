package com.example.caspian.taskmanager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    public static final String ID = "com.example.caspian.taskmanager.id";
    private TextView test;
    private Task mTask;
    private TaskLab mTaskLab;

    public static DetailFragment newInstance(UUID id) {

        Bundle args = new Bundle();
        args.putSerializable(ID, id);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskLab = TaskLab.getmInstance();
        mTask = mTaskLab.getTask((UUID) getArguments().getSerializable(ID));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        test = view.findViewById(R.id.test);
        test.setText(mTask.getTitle());
        return view;
    }

}
