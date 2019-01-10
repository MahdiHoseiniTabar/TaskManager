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


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    private EditText et_username;
    private EditText et_password;
    private Button btn_signin;
    private TextView txt_signup;


    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        et_username = view.findViewById(R.id.editText_user_signin);
        et_password = view.findViewById(R.id.editText_pass_signin);
        btn_signin = view.findViewById(R.id.button_signin);
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
