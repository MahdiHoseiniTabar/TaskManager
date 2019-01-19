package com.example.caspian.taskmanager;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends DialogFragment {
    public static final String ID = "com.example.caspian.taskmanager.id";
    public static final int REQ_PICKER_CODE = 1;
    private EditText txt_discribtion;
    private EditText txt_title;
    private Button btn_date;
    private CheckBox chk_done;

    private Task mTask;
    private TaskLab mTaskLab;
    private Date date;
    private boolean flag = false;

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

        mTaskLab = TaskLab.getmInstance(getActivity());
        mTask = mTaskLab.getTask((UUID) getArguments().getSerializable(ID));

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_detail, null);

        txt_title = view.findViewById(R.id.title_txt);
        txt_discribtion = view.findViewById(R.id.describtion);
        btn_date = view.findViewById(R.id.date);
        chk_done = view.findViewById(R.id.checkBox_done_edit);

        txt_title.setText(mTask.getTitle());
        btn_date.setText(mTask.dateToString());
        txt_discribtion.setText(mTask.getDescribtion());
        chk_done.setChecked(mTask.isDone());

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getDate());
                datePickerFragment.setTargetFragment(DetailFragment.this, REQ_PICKER_CODE);
                datePickerFragment.show(getFragmentManager(), "dialog");

            }
        });


        return new AlertDialog.Builder(getActivity())
                .setTitle("edit Task")
                .setView(view)
                .setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (txt_title.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Every Task must have a Title", Toast.LENGTH_SHORT).show();
                        } else {
                            Task newTask = new Task();
                            newTask.setTitle(txt_title.getText().toString());
                            newTask.setDescribtion(txt_discribtion.getText().toString());
                            newTask.setDone(chk_done.isChecked());
                            if (flag)
                                newTask.setDate(date);
                            mTaskLab.editTask(newTask, mTask);
                            ((ListActivity) getActivity()).myOnResume();
                        }
                    }
                })
                .setNegativeButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTaskLab.deleteTask(mTask);
                        ((ListActivity) getActivity()).myOnResume();
                    }
                })
                .show();
    }




   /* public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done_imageButton:
                Task newTask = mTaskLab.doneTask(mTask);
                mTaskLab.editTask(newTask, mTask);
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
    }*/

 /*   @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_list_all,null);

    }*/

    @Override
    public void onResume() {
        super.onResume();
        txt_title.setText(mTask.getTitle());
        btn_date.setText(mTask.dateToString());
        txt_discribtion.setText(mTask.getDescribtion());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQ_PICKER_CODE) {
            date = (Date) data.getSerializableExtra(DatePickerFragment.INTENT_DATE);
            flag = data.getBooleanExtra(DatePickerFragment.INTENT_BOOLEAN , false);
            mTask.setDate(date);
            btn_date.setText(mTask.dateToString());
        }
    }
}
