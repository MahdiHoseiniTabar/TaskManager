package com.example.caspian.taskmanager;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragmentDone extends Fragment {
    private RecyclerView mRecyclerView;
    private ListFragmentDone.DoneTaskAdapter doneTaskAdapterr;
    private TaskLab mTaskLab;
    private List<Task> mTaskListDone;


    public ListFragmentDone() {
        // Required empty public constructor
    }

    public static ListFragmentDone newInstance() {

        Bundle args = new Bundle();

        ListFragmentDone fragment = new ListFragmentDone();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskLab = TaskLab.getmInstance();
        mTaskListDone = mTaskLab.getDoneTasks();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_done, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_done);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (doneTaskAdapterr == null)
            doneTaskAdapterr = new DoneTaskAdapter();
        mRecyclerView.setAdapter(doneTaskAdapterr);

        return view;
    }

    public class DoneTaskAdapter extends RecyclerView.Adapter<DoneTaskAdapter.DoneTaskholder> {

        @NonNull
        @Override
        public DoneTaskholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.item_list_done, parent, false);
            DoneTaskholder doneTaskholder = new DoneTaskholder(view);
            return doneTaskholder;
        }

        @Override
        public void onBindViewHolder(@NonNull DoneTaskholder holder, int position) {
            Task task = mTaskLab.getTask(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return mTaskListDone.size();
        }

        public class DoneTaskholder extends RecyclerView.ViewHolder {
            private CircleImageView mCircleImageView;
            private TextView icon;
            private TextView title;
            private TextView date;

            public DoneTaskholder(View itemView) {
                super(itemView);
                mCircleImageView = itemView.findViewById(R.id.item_list_all_circle_image);
                icon = itemView.findViewById(R.id.circle_image_text);
                title = itemView.findViewById(R.id.item_list_all_title);
                date = itemView.findViewById(R.id.item_list_all_date);
            }
            public  void bind(Task task){
                mCircleImageView.setCircleBackgroundColor(Color.BLACK);
                icon.setText(task.getTitle());
                title.setText(task.getTitle());
                date.setText(task.getDate().toString());

            }
        }
    }
}
