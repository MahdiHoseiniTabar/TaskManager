package com.example.caspian.taskmanager;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragmentAll extends Fragment {
    public static final String POSISION = "com.example.caspian.taskmanager.posision";
    public static final int REQ_CODE = 0;
    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;
    private TaskLab mTaskLab;
    private List<Task> mTaskList;
    private int posision;


    public static ListFragmentAll newInstance(int posision) {

        Bundle args = new Bundle();
        args.putInt(POSISION, posision);
        ListFragmentAll fragment = new ListFragmentAll();
        fragment.setArguments(args);
        return fragment;
    }

    public ListFragmentAll() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("alll", "onCreate: ");

        posision = getArguments().getInt(POSISION);
        mTaskLab = TaskLab.getmInstance(getActivity());
        if (posision == 0)
            mTaskList = mTaskLab.getTaskList();
        if (posision == 1)
            mTaskList = mTaskLab.getDoneTaskList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_all, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_all);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (mTaskAdapter == null)
            mTaskAdapter = new TaskAdapter(mTaskList, getActivity());
        mRecyclerView.setAdapter(mTaskAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("poss>", "onResume: " + posision);
        if (posision == 0)
            mTaskList = mTaskLab.getTaskList();
        if (posision == 1)
            mTaskList = mTaskLab.getDoneTaskList();
        if (mTaskAdapter == null)
            mTaskAdapter = new TaskAdapter(mTaskList, getActivity());
        mTaskAdapter.setTaskList(mTaskList);
        mTaskAdapter.notifyDataSetChanged();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQ_CODE){
            /*mTaskList = mTaskLab.getTaskList();
            mTaskList = mTaskLab.getDoneTaskList();
            Log.i("<><>", "onActivityResult: " + mTaskList.size());
            mTaskAdapter.setTaskList(mTaskList);
            mTaskAdapter.notifyDataSetChanged();*/
        }
    }

    public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.Taskholder> {

        private List<Task> mTaskList;
        private Context mContext;


        public TaskAdapter(List<Task> taskList, Context context) {
            mTaskList = taskList;
            mContext = context;
        }

        @NonNull
        @Override
        public TaskAdapter.Taskholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View view = layoutInflater.inflate(R.layout.item_list_all, parent, false);
            TaskAdapter.Taskholder taskHolder = new TaskAdapter.Taskholder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskAdapter.Taskholder holder, int position) {
            Task task = mTaskLab.getTask(position, mTaskList);
            holder.bind(task);

        }

        @Override
        public int getItemCount() {
            return mTaskList.size();
        }

        public void setTaskList(List<Task> taskList) {
            this.mTaskList = taskList;
        }


        public class Taskholder extends RecyclerView.ViewHolder {
            public RelativeLayout root;
            private CircleImageView mCircleImageView;
            private TextView icon;
            private TextView title;
            private TextView date;
            private Button edit;

            public Taskholder(View itemView) {
                super(itemView);
                root = itemView.findViewById(R.id.root_element);
                mCircleImageView = itemView.findViewById(R.id.item_list_all_circle_image);
                icon = itemView.findViewById(R.id.circle_image_text);
                title = itemView.findViewById(R.id.item_list_all_title);
                date = itemView.findViewById(R.id.item_list_all_date);
                edit = itemView.findViewById(R.id.button_edit_list);
            }

            public void bind(final Task task) {
                mCircleImageView.setCircleBackgroundColor(Color.BLACK);
                icon.setText(task.getTitle());
                title.setText(task.getTitle());
                date.setText(task.dateToString());
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DetailFragment detailFragment = DetailFragment.newInstance(task.getId());
                        detailFragment.setTargetFragment(ListFragmentAll.this, REQ_CODE);
                        detailFragment.show(getFragmentManager(), "dialog");
                    }
                });
                root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*mContext.startActivity(DetailActivity.newIntent(mContext, task.getId()));*/
                        DetailDialogFragment detailDialogFragment = DetailDialogFragment.newInstance(task.getId());
                        detailDialogFragment.show(getFragmentManager(), "dialog");
                    }
                });
            }

        }

    }
}
