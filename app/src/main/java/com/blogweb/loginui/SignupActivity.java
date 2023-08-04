package com.blogweb.loginui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;

    private EditText fullname, emailET, mobileET, passwordETsignup, conPasswordSignup;
    private Button signupBtn;
    private TextView signInBtn;
    private ImageView passwordIcon, conPasswordIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullname = findViewById(R.id.fullname);
        emailET = findViewById(R.id.emailET);
        mobileET = findViewById(R.id.mobileET);

        passwordETsignup = findViewById(R.id.passwordET);
        conPasswordSignup = findViewById(R.id.conpasswordET);

        signupBtn = findViewById(R.id.SignUpBtn);
        signInBtn = findViewById(R.id.signInBtn);

        passwordIcon = findViewById(R.id.passwordIcon);
        conPasswordIcon = findViewById(R.id.conpasswordIcon);


        passwordIcon.setOnClickListener(v -> {
            if (passwordShowing) {
                passwordShowing = false;

                passwordETsignup.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                passwordIcon.setImageResource(R.drawable.show_password);


            } else {
                passwordShowing = true;
                passwordETsignup.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordIcon.setImageResource(R.drawable.hide_password);

            }
            //move cursor at last of the text
            passwordETsignup.setSelection(passwordETsignup.length());
        });
        conPasswordIcon.setOnClickListener(v -> {
            if (conPasswordShowing) {
                conPasswordShowing = false;

                conPasswordSignup.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                conPasswordIcon.setImageResource(R.drawable.show_password);


            } else {
                conPasswordShowing = true;
                conPasswordSignup.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                conPasswordIcon.setImageResource(R.drawable.hide_password);

            }
            //move cursor at last of the text
            conPasswordSignup.setSelection(passwordETsignup.length());
        });

        signupBtn.setOnClickListener(v->{

            String getMobileText = mobileET.getText().toString();
            String getEmailtext = emailET.getText().toString();

            Intent intent = new Intent(SignupActivity.this,OTPVerification.class);
            intent.putExtra("mobile",getMobileText);
            intent.putExtra("email",getEmailtext);
            startActivity(intent);
        });

        signInBtn.setOnClickListener(v -> {
            finish();
        });
    }
}