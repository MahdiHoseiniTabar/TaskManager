package com.example.caspian.taskmanager.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caspian.taskmanager.R;
import com.example.caspian.taskmanager.model.Account;
import com.example.caspian.taskmanager.model.AccountLab;

import java.util.UUID;


public class SignUpFragment extends Fragment {
    public static final String ID = "com.example.caspian.taskmanager.accId";
    private EditText et_username;
    private EditText et_password;
    private Button btn_signup;
    private Account mAccount;
    private AccountLab mAccountLab;


    public static SignUpFragment newInstance(UUID id) {

        Bundle args = new Bundle();
        args.putSerializable(ID,id);
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("back<", "onCreateView: " + Account.isIsGUess());
        mAccount = new Account();
        mAccountLab = AccountLab.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        et_password = view.findViewById(R.id.editText_pass_signup);
        et_username = view.findViewById(R.id.editText_user_signup);
        btn_signup = view.findViewById(R.id.button_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_password.getText().toString().equals("guess") || et_username.getText().toString().equals("guess"))
                    Toast.makeText(getActivity(), "this Account reserved!", Toast.LENGTH_SHORT).show();
                else {
                    if (et_password.getText().toString().equals("") || et_username.getText().toString().equals(""))
                        Toast.makeText(getActivity(), "Username and Password couldn't null", Toast.LENGTH_SHORT).show();
                    else {
                        mAccount.setUsername(et_username.getText().toString());
                        mAccount.setPassword(et_password.getText().toString());
                        if (mAccountLab.accountIsExist(mAccount)) {
                            Toast.makeText(getActivity(), "This username exist!", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(getActivity(), "Your Account Created!", Toast.LENGTH_SHORT).show();
                            if (Account.isIsGUess()) {
                                Account.setIsGUess(false);

                                mAccountLab.updateAccount(mAccount, (UUID) getArguments().getSerializable(ID));
                                getActivity().finish();
                            } else {
                                mAccountLab.addAccount(mAccount);
                                getFragmentManager().beginTransaction().replace(R.id.verify_container, new SignInFragment()).commit();
                            }
                        }
                    }
                }
            }
        });

        return view;
    }

}
