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
import android.widget.TextView;
import android.widget.Toast;

import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends DialogFragment {
    public static final int REQ_CODE = 2;
    private EditText ed_title;
    private EditText ed_Describtion;
    private CheckBox chkbx_done;
    private TextView txt_addDate;

    private boolean flag = false;

    private Task task;
    private TaskLab mTaskLab;
    private Date date;

    public static TaskFragment newInstance() {

        Bundle args = new Bundle();

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public TaskFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_task, null);

        ed_title = view.findViewById(R.id.editText_title);
        ed_Describtion = view.findViewById(R.id.editText_Describtion);
        chkbx_done = view.findViewById(R.id.checkBox_Done);
        txt_addDate = view.findViewById(R.id.text_date);

        task = new Task();
        txt_addDate.setText(task.dateToString());
        txt_addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(task.getDate());
                datePickerFragment.setTargetFragment(TaskFragment.this, REQ_CODE);
                datePickerFragment.show(getFragmentManager(), "dialog");
            }
        });


        mTaskLab = TaskLab.getmInstance(getActivity());


        return new AlertDialog.Builder(getActivity())
                .setTitle("ADD NEW TASK")
                .setView(view)
                .setCancelable(true)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ed_title.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "every Task must have a Title", Toast.LENGTH_SHORT).show();
                        } else {

                            task.setTitle(ed_title.getText().toString());
                            task.setDescribtion(ed_Describtion.getText().toString());
                            Log.i(">>>>", "onClick: " + flag);
                            if (flag)
                                task.setDate(date);
                            task.setDone(chkbx_done.isChecked());
                            mTaskLab.addTask(task);
                            ((ListActivity) getActivity()).myOnResume();
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQ_CODE) {
            date = (Date) data.getSerializableExtra(DatePickerFragment.INTENT_DATE);
            flag = data.getBooleanExtra(DatePickerFragment.INTENT_BOOLEAN, false);
            task.setDate(date);
            txt_addDate.setText(task.dateToString());
        }
    }
}
