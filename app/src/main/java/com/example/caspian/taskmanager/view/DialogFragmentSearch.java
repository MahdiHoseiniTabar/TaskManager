package com.example.caspian.taskmanager.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.caspian.taskmanager.CallBack;
import com.example.caspian.taskmanager.R;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentSearch extends Fragment {
    public static final String TEXT_SEARCH = "com.example.caspian.taskmanager.txt";
    public static final String SEARCH = "com.example.caspian.taskmanager.boolean";
    public static final int REQ_CODE = 5;
    private EditText et_search;
    private RecyclerView mRecyclerView;
    private TaskLab mTaskLab;
    private List<Task> mTaskList;
    private TaskAdapter mTaskAdapter;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskLab = TaskLab.getmInstance(getActivity());

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_fragment_search, container, false);

        et_search = view.findViewById(R.id.editText_search);
        mRecyclerView = view.findViewById(R.id.rcycler_search);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTaskList = mTaskLab.getTaskList();
        Log.i("searchh", "onCreateView: " + mTaskList.size());

        if (mTaskAdapter == null)
            mTaskAdapter = new TaskAdapter(mTaskList, getActivity());
        mRecyclerView.setAdapter(mTaskAdapter);


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                updateUI(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateUI(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateUI(s.toString());
            }
        });
        return view;
    }


    private void updateUI(String s) {
        if (s.equals(""))
            mTaskList = mTaskLab.getTaskList();
        else
            mTaskList = mTaskLab.searchTask(s);
        mTaskAdapter.setTaskList(mTaskList);
        mTaskAdapter.notifyDataSetChanged();
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
        public DialogFragmentSearch.TaskAdapter.Taskholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View view = layoutInflater.inflate(R.layout.item_search, parent, false);
            Taskholder taskHolder = new Taskholder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DialogFragmentSearch.TaskAdapter.Taskholder holder, int position) {
            Task task = mTaskList.get(position);
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
            // private TextView icon;
            private TextView title;
            private TextView describe;


            public Taskholder(View itemView) {
                super(itemView);
                root = itemView.findViewById(R.id.root_element);
                mCircleImageView = itemView.findViewById(R.id.item_list_all_circle_image);
                // icon = itemView.findViewById(R.id.circle_image_text);
                title = itemView.findViewById(R.id.item_list_all_title);
                describe = itemView.findViewById(R.id.item_describe);

            }

            public void bind(final Task task) {
                // mCircleImageView.setCircleBackgroundColor(Color.BLACK);
                mCircleImageView.setImageResource(R.drawable.task);
                //  icon.setText(task.getTitle());
                title.setText(task.getMTitle());
                describe.setText(task.getMDescribtion());
                root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*mContext.startActivity(DetailActivity.newIntent(mContext, task.getId()));*/
                        DialogFragmentShow dialogFragmentShow = DialogFragmentShow.newInstance(task.getMId());
                        dialogFragmentShow.show(getFragmentManager(), "dialog");
                    }
                });
            }
        }
    }
}
