package com.example.caspian.taskmanager.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.caspian.taskmanager.DetailActivity;
import com.example.caspian.taskmanager.R;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.Taskholder> {
    private TaskLab mTaskLab;
    private List<Task> mTaskList;
    private Context mContext;

    public TaskAdapter(List<Task> taskList, Context context){

        mTaskList = taskList;
        mContext = context;
        mTaskLab = TaskLab.getmInstance();
    }

    @NonNull
    @Override
    public Taskholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_list_all, parent, false);
        Taskholder taskHolder = new Taskholder(view);
        return taskHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Taskholder holder, int position) {
        Task task = mTaskLab.getTask(position);
        holder.bind(task);

    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public class Taskholder extends RecyclerView.ViewHolder{
        public RelativeLayout root;
        private CircleImageView mCircleImageView;
        private TextView icon;
        private TextView title;
        private TextView date;

        public Taskholder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root_element);
            mCircleImageView = itemView.findViewById(R.id.item_list_all_circle_image);
            icon = itemView.findViewById(R.id.circle_image_text);
            title = itemView.findViewById(R.id.item_list_all_title);
            date = itemView.findViewById(R.id.item_list_all_date);
        }
        public void bind(final Task task) {
            mCircleImageView.setCircleBackgroundColor(Color.BLACK);
            icon.setText(task.getTitle());
            title.setText(task.getTitle());
            date.setText(task.getDate().toString());

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(DetailActivity.newIntent(mContext, task.getId()));
                }
            });
        }

    }

}
