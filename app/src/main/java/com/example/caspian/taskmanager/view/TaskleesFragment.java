package com.example.caspian.taskmanager.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.caspian.taskmanager.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskleesFragment extends Fragment {
    private ImageView mImageView;


    public TaskleesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tasklees, container, false);
        mImageView = view.findViewById(R.id.icon_image);
        mImageView.setImageResource(R.drawable.task);
        return view;
    }

}
