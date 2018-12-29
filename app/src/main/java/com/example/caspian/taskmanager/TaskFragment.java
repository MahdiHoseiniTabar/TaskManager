package com.example.caspian.taskmanager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.caspian.taskmanager.model.TaskLab;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {
    private EditText ed_title;
    private EditText ed_Describtion;
    private CheckBox chkbx_done;
    private Button btn_save;

    private TaskLab mTaskLab;
    private String title;
    private String describtion;
    private boolean done;
    private Date date;



    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskLab = TaskLab.getmInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        ed_title = view.findViewById(R.id.editText_title);
        ed_Describtion = view.findViewById(R.id.editText_Describtion);
        chkbx_done = view.findViewById(R.id.checkBox_Done);
        btn_save = view.findViewById(R.id.button_save);




        date = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK).getTime();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = ed_title.getText().toString();
                describtion = ed_Describtion.getText().toString();
                done = chkbx_done.isChecked();
                mTaskLab.addTask(title,describtion,date,done);
                getActivity().finish();

            }
        });
        return view;
    }

}
