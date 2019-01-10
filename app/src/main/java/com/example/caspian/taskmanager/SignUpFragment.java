package com.example.caspian.taskmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caspian.taskmanager.database.TaskDbSchema;
import com.example.caspian.taskmanager.model.Account;
import com.example.caspian.taskmanager.model.AccountLab;


public class SignUpFragment extends Fragment {
    private EditText et_username;
    private EditText et_password;
    private Button btn_signup;
    private Account mAccount;
    private AccountLab mAccountLab;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAccount = new Account();
        mAccountLab = AccountLab.getInstance(getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        et_password = view.findViewById(R.id.editText_user_signup);
        et_username = view.findViewById(R.id.editText_pass_signup);
        btn_signup = view.findViewById(R.id.button_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccount.setUsername(et_username.getText().toString());
                mAccount.setPassword(et_password.getText().toString());
                mAccountLab.addAccount(mAccount);
                Toast.makeText(getActivity(), "Your Account Created!" , Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.verify_container, new SignInFragment()).commit();
            }
        });

        return view;
    }

}
