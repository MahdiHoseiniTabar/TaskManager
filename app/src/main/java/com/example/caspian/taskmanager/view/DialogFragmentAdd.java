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
import android.util.Log;
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
import com.example.caspian.taskmanager.model.AccountLab;
import com.example.caspian.taskmanager.model.Task;
import com.example.caspian.taskmanager.model.TaskLab;

import java.io.File;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentAdd extends DialogFragment {
    public static final int REQ_CAMERA = 0;
    public static final int REQ_CODE = 2;
    private EditText ed_title;
    private EditText ed_Describtion;
    private CheckBox chkbx_done;
    private ImageView photo;
    private TextView txt_addDate;

    private boolean flag = false;

    private Task task;
    private TaskLab mTaskLab;
    private Date date;

    private File photoFile;

    public static DialogFragmentAdd newInstance() {

        Bundle args = new Bundle();

        DialogFragmentAdd fragment = new DialogFragmentAdd();
        fragment.setArguments(args);
        return fragment;
    }


    public DialogFragmentAdd() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskLab = TaskLab.getmInstance(getActivity());
        task = new Task();
        photoFile = TaskLab.getmInstance(getActivity()).getPhotoFile(task);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add, null);

        ed_title = view.findViewById(R.id.editText_title);
        ed_Describtion = view.findViewById(R.id.editText_Describtion);
        chkbx_done = view.findViewById(R.id.checkBox_Done);
        txt_addDate = view.findViewById(R.id.text_date);
        photo = view.findViewById(R.id.imageView_add);
       // UpdatePhoto();



        txt_addDate.setText(task.dateToString());
        txt_addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(task.getMDate());
                datePickerFragment.setTargetFragment(DialogFragmentAdd.this, REQ_CODE);
                datePickerFragment.show(getFragmentManager(), "dialog");
            }
        });



        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri = getUriForFile();
                task.setPhotoAddress(photoFile.getPath());
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                List<ResolveInfo> resolveInfos = getActivity().getPackageManager().queryIntentActivities(cameraIntent,PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resolveInfos){
                    getActivity().grantUriPermission(resolveInfo.activityInfo.packageName,uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                startActivityForResult(cameraIntent,REQ_CAMERA);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle("ADD NEW TASK")
                .setView(view)
                .setCancelable(true)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ed_title.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "every Task must have a Title", Toast.LENGTH_SHORT).show();
                        } else {

                            task.setMTitle(ed_title.getText().toString());
                            task.setMDescribtion(ed_Describtion.getText().toString());
                            Log.i(">>>>", "onClick: " + flag);
                            if (flag)
                                task.setMDate(date);
                            task.setMDone(chkbx_done.isChecked());
                            task.setMaccountId(AccountLab.accountId);
                            mTaskLab.addTask(task);
                            CallBack callBack = (CallBack) getActivity();
                            callBack.callBack();
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private Uri getUriForFile() {
        return FileProvider.getUriForFile(getActivity(),"com.example.caspian.taskmanager.fileProvider",photoFile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQ_CODE) {
            date = (Date) data.getSerializableExtra(DatePickerFragment.INTENT_DATE);
            flag = data.getBooleanExtra(DatePickerFragment.INTENT_BOOLEAN, false);
            task.setMDate(date);
            txt_addDate.setText(task.dateToString());
        }if (requestCode == REQ_CAMERA){
            getActivity().revokeUriPermission(getUriForFile(),Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            UpdatePhoto();
        }
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
