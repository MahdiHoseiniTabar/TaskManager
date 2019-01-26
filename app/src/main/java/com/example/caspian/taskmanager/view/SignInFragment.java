package com.example.caspian.taskmanager.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.example.caspian.taskmanager.R;
import com.example.caspian.taskmanager.model.AccountLab;
import com.example.caspian.taskmanager.model.Account;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    private EditText et_username;
    private EditText et_password;
    private Button btn_signin;
    private Button btn_guess;

    private TextView txt_signup;

    private AccountLab mAccountLab;


    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mAccountLab = AccountLab.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        et_username = view.findViewById(R.id.editText_user_signin);
        et_password = view.findViewById(R.id.editText_pass_signin);
        btn_signin = view.findViewById(R.id.button_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account mAccount = new Account();
                mAccount.setUsername(et_username.getText().toString());
                mAccount.setPassword(et_password.getText().toString());
                if(mAccountLab.getAccount(mAccount) == null){
                    Toast.makeText(getActivity(), "Username or Password not correct",Toast.LENGTH_SHORT).show();

                }else {
                    mAccountLab.setAccountId(mAccount);
                    AccountLab.setIsGUess(false);
                    startActivity(ListActivity.newIntent(getActivity(), mAccount.getAccountId()));
                }
            }
        });
        btn_guess = view.findViewById(R.id.button_guess);
        btn_guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountLab.setIsGUess(true);
                Account mAccount = new Account();
                mAccount.setPassword("guess");
                mAccount.setUsername("guess");
                mAccountLab.addAccount(mAccount);
                mAccountLab.setAccountId(mAccount);
                startActivity(ListActivity.newIntent(getActivity(), mAccount.getAccountId()));
            }
        });


        txt_signup = view.findViewById(R.id.textView_signup);
        final SignUpFragment signUpFragment = new SignUpFragment();
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                Log.i(">>>>>", "onClick: up");
                if (fragmentManager.findFragmentById(R.id.signUp_fragment) == null){
                    fragmentManager.beginTransaction()
                            .replace(R.id.verify_container,signUpFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return view;
    }

}
