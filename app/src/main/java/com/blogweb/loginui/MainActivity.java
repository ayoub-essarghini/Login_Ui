package com.blogweb.loginui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean passwordShowing = false;

    private EditText usernameET , passwordET;
    private TextView signupBtn,forgot;
    private ImageView passwordIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameET =findViewById(R.id.usernameET);
        passwordET =findViewById(R.id.passwordET);
        signupBtn =findViewById(R.id.signupBtn);
        passwordIcon =findViewById(R.id.passwordIcon);


        passwordIcon.setOnClickListener(v->{
            if (passwordShowing){
                passwordShowing=false;

                passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                passwordIcon.setImageResource(R.drawable.show_password);


            }else {
                passwordShowing=true;
                passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordIcon.setImageResource(R.drawable.hide_password);

            }
            //move cursor at last of the text
            passwordET.setSelection(passwordET.length());
        });

        signupBtn.setOnClickListener(v->{
            startActivity(new Intent(this,SignupActivity.class));
        });

    }
}