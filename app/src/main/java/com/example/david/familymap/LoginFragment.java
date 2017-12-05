package com.example.david.familymap;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import data.DataManager;
import request.LoginRequest;
import request.RegisterRequest;
import task.LoginTask;
import task.RegisterTask;


/**
 * Created by david on 11/16/17.
 */

public class LoginFragment extends android.support.v4.app.Fragment
{
    private EditText hostField;
    private EditText portField;
    private EditText userNameField;
    private EditText passwordField;
    private EditText firstNameField;
    private EditText lastNameField;
    private EditText emailField;
    private RadioGroup genderGroup;
    private Button mSignInButton;
    private Button mRegisterButton;
    private String host_;
    private String port_;
    private String userName_;
    private String password_;
    private String firstName_;
    private String lastName_;
    private String email_;
    private String gender_;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mSignInButton = (Button)v.findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // check to see if fields have been entered ???????
                mSignInButton.setEnabled(false);
                mRegisterButton.setEnabled(false);
                DataManager dman = DataManager.getInstance();
                dman.serverHost = host_;
                dman.serverPort = port_;
                LoginRequest request = new LoginRequest(userName_,password_);
                LoginTask task = new LoginTask(getActivity(),mSignInButton, mRegisterButton);
                task.execute(request);
            }

        });

        mRegisterButton = (Button)v.findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // check to see if fields have been entered ???????
                mSignInButton.setEnabled(false);
                mRegisterButton.setEnabled(false);
                DataManager dman = DataManager.getInstance();
                dman.serverHost = host_;
                dman.serverPort = port_;
                RegisterRequest request = new RegisterRequest(userName_, password_, email_, firstName_, lastName_, gender_);
                RegisterTask task = new RegisterTask(getActivity(), mSignInButton, mRegisterButton);
                task.execute(request);
                //mSignInButton.setEnabled(true);
                //mRegisterButton.setEnabled(true);
            }

        });

        hostField = (EditText)v.findViewById(R.id.host);
        hostField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                host_ = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        portField = (EditText)v.findViewById(R.id.port);
        portField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                port_ = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userNameField = (EditText)v.findViewById(R.id.user_name);
        userNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                userName_ = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordField = (EditText)v.findViewById(R.id.password);
        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                password_ = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        firstNameField = (EditText)v.findViewById(R.id.first_name);
        firstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                firstName_ = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lastNameField = (EditText)v.findViewById(R.id.last_name);
        lastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                lastName_ = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        emailField = (EditText)v.findViewById(R.id.email);
        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                email_ = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        genderGroup = (RadioGroup)v.findViewById(R.id.gender_group);
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {
                switch(genderGroup.getCheckedRadioButtonId())
                {
                    case R.id.male_button:
                            gender_ = "m";
                        break;
                    case R.id.female_button:
                            gender_ = "f";
                        break;
                    default:
                            gender_ = "f";
                        break;
                }

            }

        });



        return v;
    }

}
