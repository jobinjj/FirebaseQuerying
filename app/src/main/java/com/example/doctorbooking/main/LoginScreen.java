package com.example.doctorbooking.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.doctorbooking.R;
import com.example.doctorbooking.model.FirestoreModel;
import com.example.doctorbooking.util.AppController;
import com.example.doctorbooking.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginScreen extends AppCompatActivity {

    private EditText ed_mobile,ed_pass;
    private Button button3,btn_login_with_otp,btn_login_with_pass;
    private boolean isUsernameOk = false;
    private String verify_url = "http://control.msg91.com/api/sendotp.php?";
    private ConstraintLayout container_buttons;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private ViewGroup transitionsContainer;
    private boolean isPasswordOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        setupUI(findViewById(R.id.transitions_container ));

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            Intent intent = new Intent(LoginScreen.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        onClick();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {
        transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
        container_buttons = transitionsContainer.findViewById(R.id.container_buttons);
        ed_mobile = findViewById(R.id.ed_mobile);
        ed_pass= findViewById(R.id.ed_password);
        button3 = findViewById(R.id.button3);
        btn_login_with_otp = findViewById(R.id.btn_login_with_otp);
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
                if (ed_pass.getText().toString().equals("")) {
                    isPasswordOk = false;
                    ed_pass.setError("Enter password");

                } else {
                    isPasswordOk = true;
                }
                if (isUsernameOk && isPasswordOk) {
                    progressDialog = new ProgressDialog(LoginScreen.this);
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setMessage("Please wait...");
                    db.collection("DoctorBooking").document(ed_mobile.getText().toString())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if(task.isSuccessful()){
                                        FirestoreModel user = Objects.requireNonNull(task.getResult()).toObject(FirestoreModel.class);
                                        //Navigate to profile page
                                        if(user != null){
                                            progressDialog.dismiss();
                                            btn_login_with_otp.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    sendOtp(ed_mobile.getText().toString());
                                                }
                                            });
                                            Log.d("tag",user.getMobile());
                                            if (ed_pass.getText().toString().equals(user.getPassword())){
                                                Toast.makeText(LoginScreen.this, "Passwords match", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(LoginScreen.this,HomeActivity.class);
                                                startActivity(intent);
                                            }else {
                                                Toast.makeText(LoginScreen.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(LoginScreen.this, "Account not found Please register", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginScreen.this,RegisterActivity.class);
                                            startActivity(intent);
                                        }

                                    }else{
                                        String excep = Objects.requireNonNull(task.getException()).getMessage();
                                        Log.d("tag", "Error reading user data " + excep);
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginScreen.this, "user does not exist please register", Toast.LENGTH_SHORT).show();
                                }
                            });
//                    db.collection("DoctorBooking").document(ed_mobile.getText().toString())
//                            .get()
//                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    if(task.isSuccessful()){
//                                        FirestoreModel user = Objects.requireNonNull(task.getResult()).toObject(FirestoreModel.class);
//                                        if(user != null){
//                                            progressDialog.dismiss();
//                                            TransitionManager.beginDelayedTransition(transitionsContainer);
//                                            container_buttons.setVisibility(View.VISIBLE);
//                                            btn_login_with_otp.setOnClickListener(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//                                                    sendOtp(ed_mobile.getText().toString());
//                                                }
//                                            });
//
//                                        }else{
//                                            Toast.makeText(LoginScreen.this, "user does not exist please register", Toast.LENGTH_SHORT).show();
//                                            Intent intent = new Intent(LoginScreen.this,RegisterActivity.class);
//                                            startActivity(intent);
//                                            finish();
//                                        }
//
//                                    }else{
//                                        String excep = Objects.requireNonNull(task.getException()).getMessage();
//                                        Log.d("tag", "Error reading user data " + excep);
//                                    }
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//
//                                }
//                            });

                }

            }
        });


    }

    private void sendOtp(final String mobile) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, verify_url, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginScreen.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            if (new JSONObject(response).getString("type").equals("success")){
                                Log.d("response",response);
                                Toast.makeText(LoginScreen.this, response, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginScreen.this, RegisterOtpActivity.class);
                                intent.putExtra("mobile", ed_mobile.getText().toString());
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginScreen.this, "Error", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                int statuscode = response.statusCode;
                return super.parseNetworkResponse(response);
            }

            @Override
            public Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("authkey","271495ArJicmJ3DHDl5cac0ce9");
                params.put("message","Your verification code is ##OTP##");
                params.put("sender","TESTIN");
                params.put("mobile","91" + mobile);
                return params;
            }
//            @Override
//            public byte[] getBody() throws AuthFailureError {
//                String str = "{\"category_id\":\"1\"}";
//                return str.getBytes();
//            }
        };
        stringRequest.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void goToRegisterActivity(View view) {
        Intent intent = new Intent(LoginScreen.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
