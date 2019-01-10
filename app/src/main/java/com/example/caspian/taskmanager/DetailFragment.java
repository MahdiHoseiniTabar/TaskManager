package com.example.caspian.taskmanager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {
    public static final String ID = "com.example.caspian.taskmanager.id";
    private TextView txt_discribtion;
    private TextView txt_title;
    private TextView txt_date;
    private ImageButton imgbtn_done;
    private ImageButton imgbtn_edit;
    private ImageButton imgbtn_delete;

    private Task mTask;
    private TaskLab mTaskLab;
    private MyDialogFragment mMyDialogFragment;

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

        txt_title = view.findViewById(R.id.title_txt);
        txt_discribtion = view.findViewById(R.id.describtion);
        txt_date = view.findViewById(R.id.date);
        imgbtn_done = view.findViewById(R.id.done_imageButton);
        imgbtn_delete = view.findViewById(R.id.delete);
        imgbtn_edit = view.findViewById(R.id.edit);
        Log.i("***", "onCreateView: " + mTask.getTitle());
        txt_title.setText(mTask.getTitle());
        txt_date.setText(mTask.dateToString());
        txt_discribtion.setText(mTask.getDescribtion());

        if (mTask.isDone())
            imgbtn_done.setVisibility(View.GONE);
        imgbtn_done.setOnClickListener(this);
        imgbtn_edit.setOnClickListener(this);
        imgbtn_delete.setOnClickListener(this);


        return view;
    }

    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done_imageButton:
                mTaskLab.doneTask(mTask);
                getActivity().finish();
                break;
            case R.id.edit:
                startActivity(TaskActivity.newIntent(getActivity(), (UUID) getArguments().getSerializable(ID)));
                getActivity().finish();
                break;
            case R.id.delete:
                mMyDialogFragment = MyDialogFragment.newInstance((UUID) getArguments().getSerializable(ID));
                mMyDialogFragment.show(getFragmentManager(),"Dialog");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        txt_title.setText(mTask.getTitle());
        txt_date.setText(mTask.dateToString());
        txt_discribtion.setText(mTask.getDescribtion());
    }
}
