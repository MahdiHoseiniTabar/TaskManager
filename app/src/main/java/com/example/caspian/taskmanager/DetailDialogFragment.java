package com.example.caspian.taskmanager;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caspian.taskmanager.database.TaskDbSchema;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailDialogFragment extends DialogFragment {
    public static final String TASKID = "taskid";
    private TextView txt_discribtion;
    private TextView txt_date;
    private Task mTask;

    public static DetailDialogFragment newInstance(UUID uuid) {

        Bundle args = new Bundle();
        args.putSerializable(TASKID, uuid);
        DetailDialogFragment fragment = new DetailDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TaskLab taskLab = TaskLab.getmInstance(getActivity());
         mTask = taskLab.getTask((UUID) getArguments().getSerializable(TASKID));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_detail_dialog,null);
        txt_discribtion = view.findViewById(R.id.describtion);
        txt_date = view.findViewById(R.id.date);
        txt_date.setText(mTask.dateToString());
        txt_discribtion.setText(mTask.getDescribtion());

        return new AlertDialog.Builder(getActivity())
                .setTitle(mTask.getTitle())
                .setView(view)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

}
