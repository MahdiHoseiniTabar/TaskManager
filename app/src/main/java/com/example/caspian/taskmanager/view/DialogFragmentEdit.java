package com.example.caspian.taskmanager.view;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caspian.taskmanager.CallBack;
import com.example.caspian.taskmanager.PictureUtils;
import com.example.caspian.taskmanager.R;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentEdit extends DialogFragment {
    public static final String ID = "com.example.caspian.taskmanager.id";
    public static final int REQ_CAMERA = 0;
    public static final int REQ_PICKER_CODE = 1;
    private EditText txt_discribtion;
    private EditText txt_title;
    private TextView btn_date;
    private CheckBox chk_done;
    private ImageView photo;
    private File photoFile;

    private Task mTask;
    private TaskLab mTaskLab;
    private Date date;
    private boolean flag = false;

    public static DialogFragmentEdit newInstance(UUID id) {

        Bundle args = new Bundle();
        args.putSerializable(ID, id);
        DialogFragmentEdit fragment = new DialogFragmentEdit();
        fragment.setArguments(args);
        return fragment;
    }


    public DialogFragmentEdit() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskLab = TaskLab.getmInstance(getActivity());
        mTask = mTaskLab.getTask((UUID) getArguments().getSerializable(ID));
        photoFile = TaskLab.getmInstance(getActivity()).getPhotoFile(mTask);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_edit, null);

        txt_title = view.findViewById(R.id.title_txt);
        txt_discribtion = view.findViewById(R.id.describtion);
        btn_date = view.findViewById(R.id.date);
        chk_done = view.findViewById(R.id.checkBox_done_edit);
        photo = view.findViewById(R.id.imageViewedit);
        UpdatePhoto();

        txt_title.setText(mTask.getMTitle());
        btn_date.setText(mTask.dateToString());
        txt_discribtion.setText(mTask.getMDescribtion());
        chk_done.setChecked(mTask.getMDone());

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getMDate());
                datePickerFragment.setTargetFragment(DialogFragmentEdit.this, REQ_PICKER_CODE);
                datePickerFragment.show(getFragmentManager(), "dialog");
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri = getUriForFile();
                mTask.setPhotoAddress(photoFile.getPath());
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                List<ResolveInfo> resolveInfos = getActivity().getPackageManager().queryIntentActivities(cameraIntent,PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resolveInfos){
                    getActivity().grantUriPermission(resolveInfo.activityInfo.packageName,uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                startActivityForResult(cameraIntent,REQ_CAMERA);
            }
        });

        final CallBack callBack = (CallBack) getActivity();
        return new AlertDialog.Builder(getActivity())
                .setTitle("edit Task")
                .setView(view)
                .setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (txt_title.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Every Task must have a Title", Toast.LENGTH_SHORT).show();
                        } else {
                            Task newTask = new Task();
                            newTask.setMTitle(txt_title.getText().toString());
                            newTask.setMDescribtion(txt_discribtion.getText().toString());
                            newTask.setMDone(chk_done.isChecked());
                            if (flag)
                                newTask.setMDate(date);
                            mTaskLab.editTask(newTask, mTask);

                            callBack.callBack();
                        }
                    }
                })
                .setNegativeButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTaskLab.deleteTask(mTask);

                        callBack.callBack();
                    }
                })
                .show();
    }
    @Override
    public void onResume() {
        super.onResume();
        txt_title.setText(mTask.getMTitle());
        btn_date.setText(mTask.dateToString());
        txt_discribtion.setText(mTask.getMDescribtion());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQ_PICKER_CODE) {
            date = (Date) data.getSerializableExtra(DatePickerFragment.INTENT_DATE);
            flag = data.getBooleanExtra(DatePickerFragment.INTENT_BOOLEAN , false);
            mTask.setMDate(date);
            btn_date.setText(mTask.dateToString());
        }if (requestCode == REQ_CAMERA){
            getActivity().revokeUriPermission(getUriForFile(),Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            UpdatePhoto();
        }
    }
    private Uri getUriForFile() {
        return FileProvider.getUriForFile(getActivity(),"com.example.caspian.taskmanager.fileProvider",photoFile);
    }
    private void UpdatePhoto(){

        if(photoFile == null|| !photoFile.exists()){
            photo.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera));
        }
        else{
            Bitmap bitmap = PictureUtils.getScaleBitmap(photoFile.getPath(),getActivity());
            photo.setImageBitmap(bitmap);
        }
    }
}
