package com.example.caspian.taskmanager.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.caspian.taskmanager.CallBack;
import com.example.caspian.taskmanager.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentSearch extends DialogFragment {
    public static final String TEXT_SEARCH = "com.example.caspian.taskmanager.txt";
    public static final String SEARCH = "com.example.caspian.taskmanager.boolean";
    private Button btn_search;
    private EditText et_search;

    public static DialogFragmentSearch newInstance() {

        Bundle args = new Bundle();

        DialogFragmentSearch fragment = new DialogFragmentSearch();
        fragment.setArguments(args);
        return fragment;
    }


    public DialogFragmentSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_fragment_search, container, false);

        btn_search = view.findViewById(R.id.button_search);
        et_search = view.findViewById(R.id.editText_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.putExtra(TEXT_SEARCH,et_search.getText().toString());
                intent.putExtra(SEARCH,true);
                getTargetFragment().onActivityResult(getTargetRequestCode(),Activity.RESULT_OK,intent);
                CallBack callBack = (CallBack) getActivity();
                callBack.callBack();
                getDialog().cancel();
            }
        });
        return view;
    }

}
