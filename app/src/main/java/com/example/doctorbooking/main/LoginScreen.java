package com.example.doctorbooking.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.doctorbooking.R;
import com.example.doctorbooking.util.Constants;

public class LoginScreen extends AppCompatActivity {

    private EditText ed_mobile;
    private Button button3,btn_login_with_otp,btn_login_with_pass;
    private boolean isUsernameOk = false;

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        setupUI(findViewById(R.id.parent));

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            Intent intent = new Intent(LoginScreen.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        onClick();
    }

    private void onClick() {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_mobile.getText().toString().equals("")) {
                    isUsernameOk = false;
                    ed_mobile.setError("Enter mobile");

                } else {
                    isUsernameOk = true;
                }
                if (isUsernameOk) {

                }

            }
        });
        btn_login_with_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setupUI(View view) {
        ed_mobile = findViewById(R.id.ed_mobile);
        button3 = findViewById(R.id.button3);
        btn_login_with_otp = findViewById(R.id.btn_login_with_otp);
        btn_login_with_pass = findViewById(R.id.btn_login_with_pass);
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LoginScreen.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }


    public void goToRegisterActivity(View view) {
        Intent intent = new Intent(LoginScreen.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
