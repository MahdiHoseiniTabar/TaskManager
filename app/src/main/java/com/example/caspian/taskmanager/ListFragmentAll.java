package com.example.caspian.taskmanager;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragmentAll extends Fragment {
    private RecyclerView mRecyclerView;
    private AllTaskAdapter allTaskAdapter;


    public static ListFragmentAll newInstance() {

        Bundle args = new Bundle();

        ListFragmentAll fragment = new ListFragmentAll();
        fragment.setArguments(args);
        return fragment;
    }

    public ListFragmentAll() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_all,container ,false);

        mRecyclerView = view.findViewById(R.id.recycler_all);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (allTaskAdapter == null)
             allTaskAdapter = new AllTaskAdapter();
        mRecyclerView.setAdapter(allTaskAdapter);

        return view;
    }

    public class AllTaskAdapter extends RecyclerView.Adapter<AllTaskAdapter.TaskHolder>{

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_list_all, parent ,false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {


        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class TaskHolder extends RecyclerView.ViewHolder{
            private CircleImageView mCircleImageView;
            private TextView title;
            private TextView date;


            public TaskHolder(View itemView) {
                super(itemView);

                mCircleImageView = itemView.findViewById(R.id.item_list_all_circle_image);
                title = itemView.findViewById(R.id.item_list_all_title);
                date = itemView.findViewById(R.id.item_list_all_date);
            }
        }
    }

}
