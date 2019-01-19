package com.example.caspian.taskmanager;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String ARG_DATE = "com.example.caspian.taskmanager.date";
    public static final String INTENT_DATE = "com.example.caspian.taskmanager.intentdate";
    private DatePicker mDatePicker;

    public static DatePickerFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public DatePickerFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_date_picker,null);
        Date mDate = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker = view.findViewById(R.id.datePicker);
        mDatePicker.init(year,month,day,null);


        return new AlertDialog.Builder(getActivity())
                .setTitle("SetDate")
                .setView(view)
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year, month, day).getTime();
                        sendResult(date);
                    }
                })
                .show();
    }

    public void sendResult(Date date){
        Intent intent = new Intent();
        intent.putExtra(INTENT_DATE,date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),Activity.RESULT_OK,intent);
        getTargetFragment().onResume();
    }
}
