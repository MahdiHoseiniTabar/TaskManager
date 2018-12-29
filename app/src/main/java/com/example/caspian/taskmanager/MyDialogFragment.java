package com.example.caspian.taskmanager;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TextView;

import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {
    public static final String ID = "com.example.caspian.taskmanager.id";
    private TextView txt_icon;
    private TextView txt_title;
    private TextView txt_date;
    private CircleImageView mCircleImageView;
    private Task mTask;
    private TaskLab mTaskLab;


    public static MyDialogFragment newInstance(UUID id) {

        Bundle args = new Bundle();
        args.putSerializable(ID, id);

        MyDialogFragment fragment = new MyDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MyDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskLab = TaskLab.getmInstance();
        mTask = mTaskLab.getTask((UUID) getArguments().getSerializable(ID));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_list_all,null);

        txt_title = view.findViewById(R.id.item_list_all_title);
        txt_icon = view.findViewById(R.id.circle_image_text);
        txt_date = view.findViewById(R.id.item_list_all_date);
        mCircleImageView = view.findViewById(R.id.item_list_all_circle_image);

        txt_title.setText(mTask.getTitle());
        txt_icon.setText(mTask.getTitle());
        txt_date.setText(mTask.getDate().toString());
        mCircleImageView.setCircleBackgroundColor(Color.BLACK);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Are you sure delete this Task?")
                .setView(view)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTaskLab.deleteTask(mTask);
                        getActivity().finish();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();

    }
}
