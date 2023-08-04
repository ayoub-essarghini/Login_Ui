package com.blogweb.loginui;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OTPVerification extends AppCompatActivity {

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            if (editable.length() >0){
                if (selectedETPosition==0){

                    selectedETPosition=1;
                    showKeyboard(otpET2);
                    
                } else if (selectedETPosition==1) {
                    selectedETPosition=2;
                    showKeyboard(otpET3);
                }
                else if (selectedETPosition==2) {
                    selectedETPosition=3;
                    showKeyboard(otpET4);
                }

            }

        }
    };

    private EditText otpET1, otpET2, otpET3, otpET4;
    private TextView resendBtn, otpEmail, otpMobile;

    private Button verify;

    //true after every 60 seconds
    private boolean resendEnabled = false;

    //resend time in seconds
    private int resendTime = 60;

    private int selectedETPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        otpET1 = findViewById(R.id.otpET1);
        otpET2 = findViewById(R.id.otpET2);
        otpET3 = findViewById(R.id.otpET3);
        otpET4 = findViewById(R.id.otpET4);

        verify = findViewById(R.id.verifyBtn);

        otpEmail = findViewById(R.id.otpEmail);
        otpMobile = findViewById(R.id.otpMobile);

        resendBtn = findViewById(R.id.resendBtn);

        String getEmail = getIntent().getStringExtra("email");
        String getMobile = getIntent().getStringExtra("mobile");

        otpMobile.setText(getMobile);
        otpEmail.setText(getEmail);


        otpET1.addTextChangedListener(textWatcher);
        otpET2.addTextChangedListener(textWatcher);
        otpET3.addTextChangedListener(textWatcher);
        otpET4.addTextChangedListener(textWatcher);

        //by default open keyboard at otpEt1

        showKeyboard(otpET1);
        startCountDownTimer();

        resendBtn.setOnClickListener(v -> {
            if (resendEnabled) {
                //resend code


                //start new Count down timer
                startCountDownTimer();
            }
        });


        verify.setOnClickListener(v -> {
            String generateOtp = otpET1.getText().toString() + otpET2.getText().toString() + otpET3.getText().toString() + otpET4.getText().toString();

            if (generateOtp.length() == 4) {

                //handle your otp
            }
        });
    }

    private void showKeyboard(EditText otpET) {
        otpET.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT);

    }


    private void startCountDownTimer() {

        resendEnabled = false;
        resendBtn.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resendTime * 1000, 1000) {

            @Override
            public void onTick(long l) {
                resendBtn.setText("Resend Code (" + (l / 1000) + ")");
            }

            @Override
            public void onFinish() {
                resendEnabled = true;
                resendBtn.setText("Resend Code");
                resendBtn.setTextColor(Color.parseColor("#2196F3"));
            }
        }.start();
    }



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_L) {
            if (selectedETPosition == 3){

                selectedETPosition =2;
                showKeyboard(otpET3);

            }else if (selectedETPosition == 2){
                selectedETPosition =1;
                showKeyboard(otpET2);

            }
            else if (selectedETPosition == 1){
                selectedETPosition =0;
                showKeyboard(otpET1);

            }
            return true;

        }else {

            return super.onKeyUp(keyCode, event);

        }

    }
}