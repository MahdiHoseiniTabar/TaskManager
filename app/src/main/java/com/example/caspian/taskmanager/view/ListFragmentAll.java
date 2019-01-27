package com.example.caspian.taskmanager.view;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.caspian.taskmanager.CallBack;
import com.example.caspian.taskmanager.PictureUtils;
import com.example.caspian.taskmanager.R;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragmentAll extends Fragment {
    public static final String POSISION = "com.example.caspian.taskmanager.posision";
    public static final int REQ_CODE_SEARCH = 1;
    public static final int REQ_CODE = 0;
    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;
    private TaskLab mTaskLab;
    private List<Task> mTaskList;
    private int posision;
    private boolean show_floating_button = true;
    private Callback mCallback;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
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
        setHasOptionsMenu(true);

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
        if (posision == 0)
            mTaskList = mTaskLab.getTaskList();
        Log.i("<<<?", "onResume: " + mTaskList.size());
        if (posision == 1)
            mTaskList = mTaskLab.getDoneTaskList();
        if (mTaskAdapter == null)
            mTaskAdapter = new TaskAdapter(mTaskList, getActivity());
        mTaskAdapter.setTaskList(mTaskList);
        mTaskAdapter.notifyDataSetChanged();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_list, menu);
        // MenuItem item = menu.findItem(R.id.delete_task);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.delete_task) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Are you sure to delete all Tasks")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mTaskLab.deleteAllTask();
                            CallBack callBack = (CallBack) getActivity();
                            callBack.callBack();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
            return true;
        } else if (item.getItemId() == R.id.search_task) {
            startActivity(new Intent(getActivity(), SearchActivity.class));
        } else if (item.getItemId() == R.id.hide_fb) {
            if (!show_floating_button) {
                mCallback.show();
                item.setTitle("Hide Floating Button");
                show_floating_button = true;
            } else {
                mCallback.hide();
                item.setTitle("Show Floating Button");
                show_floating_button = false;
            }
        }
        return false;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQ_CODE_SEARCH) {
            /*List<Task> taskList = mTaskLab.searchTask(data.getStringExtra(DialogFragmentSearch.TEXT_SEARCH));
            isSearch = data.getBooleanExtra(DialogFragmentSearch.SEARCH, false);
            if (mTaskList.size() != 0) {
                Toast.makeText(getActivity(), "tasks found! Press Back to out of search", Toast.LENGTH_LONG).show();
                mTaskAdapter.setTaskList(taskList);
                mTaskAdapter.notifyDataSetChanged();
            }
            if (mTaskList.size()  == 0) {
                Toast.makeText(getActivity(), "any task not found!", Toast.LENGTH_LONG).show();
                isSearch = false;
            }
            isSearch = data.getBooleanExtra(DialogFragmentSearch.SEARCH, false);*/
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
            // private TextView icon;
            private TextView title;
            private TextView date;
            private ImageView edit;
            private ImageView share;

            public Taskholder(View itemView) {
                super(itemView);
                root = itemView.findViewById(R.id.root_element);
                mCircleImageView = itemView.findViewById(R.id.item_list_all_circle_image);
                // icon = itemView.findViewById(R.id.circle_image_text);
                title = itemView.findViewById(R.id.item_list_all_title);
                date = itemView.findViewById(R.id.item_list_all_date);
                edit = itemView.findViewById(R.id.button_edit_list);
                share = itemView.findViewById(R.id.share);
            }

            public void bind(final Task task) {
                // mCircleImageView.setCircleBackgroundColor(Color.BLACK);
                if (task.getPhotoAddress() == null)
                    mCircleImageView.setImageResource(R.drawable.task);
                else
                    mCircleImageView.setImageBitmap(PictureUtils.getScaleBitmap(task.getPhotoAddress(), getActivity()));
                //  icon.setText(task.getTitle());
                title.setText(task.getMTitle());
                date.setText(task.dateToString());
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragmentEdit dialogFragmentEdit = DialogFragmentEdit.newInstance(task.getMId());
                        dialogFragmentEdit.setTargetFragment(ListFragmentAll.this, REQ_CODE);
                        dialogFragmentEdit.show(getFragmentManager(), "dialog");
                    }
                });
                root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*mContext.startActivity(DetailActivity.newIntent(mContext, task.getId()));*/
                        DialogFragmentShow dialogFragmentShow = DialogFragmentShow.newInstance(task.getMId());
                        dialogFragmentShow.show(getFragmentManager(), "dialog");
                    }
                });
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, getTaskText(task));
                        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
                        intent.setType("text/plain");
                        Intent intentFilter = Intent.createChooser(intent, getString(R.string.chooser));
                        startActivity(intentFilter);
                    }
                });
            }

            private String getTaskText(Task task) {
                String type = task.getMDone() ? getString(R.string.isDone) : getString(R.string.isNotDone);
                return getString(R.string.taskText, task.getMTitle(), task.getMDescribtion(), task.dateToString(), type);
            }

        }

    }

    interface Callback {
        void show();

        void hide();
    }
}
