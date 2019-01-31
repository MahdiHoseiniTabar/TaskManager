package com.example.caspian.taskmanager.view;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.caspian.taskmanager.PictureUtils;
import com.example.caspian.taskmanager.R;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowIconFragment extends DialogFragment {
    private ImageView showIcon;

    public ShowIconFragment() {
        // Required empty public constructor
    }

    public static ShowIconFragment newInstance(UUID id) {

        Bundle args = new Bundle();
        args.putSerializable("id", id);
        ShowIconFragment fragment = new ShowIconFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Task task = TaskLab.getmInstance(getActivity()).getTask((UUID) getArguments().getSerializable("id"));
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_show_icon, null);
        showIcon = view.findViewById(R.id.showimg);
        if (task.getPhotoAddress() == null)
            showIcon.setImageResource(R.drawable.task);
        else
            showIcon.setImageBitmap(PictureUtils.getScaleBitmap(task.getPhotoAddress(),750,600));
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(task.getMTitle())
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
