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
import android.widget.Toast;

import com.example.doctorbooking.R;
import com.example.doctorbooking.util.Constants;

public class LoginScreen extends AppCompatActivity {

    private EditText ed_username;
    private EditText ed_password;
    private Button button3;
    private SharedPreferences sharedPreferences;
    private boolean isusernameOk = false;
    private boolean ispasswordOk = false;

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

        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isLoggedIn",false)) {
            Intent intent = new Intent(LoginScreen.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        button3 = findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_username.getText().toString().equals("")) {
                    isusernameOk = false;
                    ed_username.setError("Enter username");

                } else {
                    isusernameOk = true;
                }
                if (ed_password.getText().toString().equals("")) {
                    ed_password.setError("Enter password");
                    isusernameOk = false;
                } else {
                    ispasswordOk = true;
                }
                if (isusernameOk && ispasswordOk) {
                    if (ed_password.getText().toString().equals(sharedPreferences.getString("password",null)) && ed_username.getText().toString().equals(sharedPreferences.getString("username",null))) {

                        Toast.makeText(LoginScreen.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        //we need editor to edit created shared preference file
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", ed_username.getText().toString()).apply();
                        editor.putBoolean("isLoggedIn",true).apply();
                        Intent intent = new Intent(LoginScreen.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else
                        Toast.makeText(LoginScreen.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void setupUI(View view) {

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
        Intent intent = new Intent(LoginScreen.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
