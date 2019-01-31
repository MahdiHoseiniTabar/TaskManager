package com.example.caspian.taskmanager.view;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caspian.taskmanager.PictureUtils;
import com.example.caspian.taskmanager.R;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentShow extends DialogFragment {
    public static final String TASKID = "taskid";
    private TextView txt_discribtion;
    private TextView txt_date;
    private ImageView photo;
    private Task mTask;

    public static DialogFragmentShow newInstance(UUID uuid) {

        Bundle args = new Bundle();
        args.putSerializable(TASKID, uuid);
        DialogFragmentShow fragment = new DialogFragmentShow();
        fragment.setArguments(args);
        return fragment;
    }

    public DialogFragmentShow() {
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


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_detail_dialog, null);
        txt_discribtion = view.findViewById(R.id.describtion);
        txt_date = view.findViewById(R.id.date);
        photo = view.findViewById(R.id.imageViewshow);
        if (mTask.getPhotoAddress() == null)
            photo.setImageResource(R.drawable.task);
        else
            photo.setImageBitmap(PictureUtils.getScaleBitmap(mTask.getPhotoAddress(), 250, 250));

        txt_date.setText(mTask.dateToString());
        txt_discribtion.setText(mTask.getMDescribtion());

        return new AlertDialog.Builder(getActivity())
                .setTitle(mTask.getMTitle())
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
