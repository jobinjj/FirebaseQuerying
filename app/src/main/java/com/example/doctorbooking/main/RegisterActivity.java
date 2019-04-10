package com.example.doctorbooking.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.doctorbooking.R;
import com.example.doctorbooking.util.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText ed_username,ed_mobile,ed_password;
    private PreferencesHelper preferencesHelper;
    private String verify_url = "http://control.msg91.com/api/sendotp.php?";
    private Button button3;

    private boolean isusernameOk = false;
    private boolean ispasswordOk = false;
    private boolean isphoneOk = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initSharedPreference();
        onClick();
    }

    private void initSharedPreference() {
        preferencesHelper = new PreferencesHelper(RegisterActivity.this);
    }

    private void onClick() {
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
                    ispasswordOk = false;
                    ed_password.setError("Enter password");

                } else {
                    ispasswordOk = true;
                }
                if (ed_mobile.getText().toString().equals("")) {
                    isphoneOk = false;
                    ed_mobile.setError("Enter mobile");

                } else {
                    isphoneOk = true;
                }
                if (ispasswordOk && isusernameOk && isphoneOk){
                    sendOtp(ed_mobile.getText().toString(),ed_username.getText().toString(),ed_password.getText().toString());
                }
                }
        });
    }

    private void sendOtp(final String mobile, String toString1, String toString2) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, verify_url, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            if (new JSONObject(response).getString("type").equals("success")){
                                Log.d("response",response);
                                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, RegisterOtpActivity.class);
                                intent.putExtra("name", ed_username.getText().toString());
                                intent.putExtra("mobile", ed_mobile.getText().toString());
                                intent.putExtra("password", ed_password.getText().toString());
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();

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

    private void initViews() {
        ed_username = findViewById(R.id.ed_username);
        ed_mobile = findViewById(R.id.ed_mobile);
        ed_password = findViewById(R.id.ed_password);
        button3 = findViewById(R.id.button3);

    }

    public void Register(View view) {
    }

    public void goToLoginActivity(View view) {
        Intent intent = new Intent(RegisterActivity.this,LoginScreen.class);
        startActivity(intent);
        finish();
    }
}
