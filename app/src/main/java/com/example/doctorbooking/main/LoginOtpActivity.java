package com.example.doctorbooking.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.doctorbooking.util.Constants;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginOtpActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText ed_digit1,ed_digit2,ed_digit3,ed_digit4;
    Button btn_verify;
    private String verify_url = "https://control.msg91.com/api/verifyRequestOTP.php?";
    private String name;
    private String mobile;
    private SharedPreferences sharedPreferences;
    private String password;
    private PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);
        intiViews();
    }
    private void intiViews() {
        preferencesHelper = new PreferencesHelper(this);
        sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        name = getIntent().getStringExtra("name");
        mobile = getIntent().getStringExtra("mobile");
        password = getIntent().getStringExtra("password");

        ed_digit1 = findViewById(R.id.ed_pass);
        ed_digit2 = findViewById(R.id.ed_digit2);
        ed_digit3 = findViewById(R.id.ed_digit3);
        ed_digit4 = findViewById(R.id.ed_digit4);
        btn_verify = findViewById(R.id.btn_verify);
    }
    private void verifyOtp(final String digit1, final String digit2, final String digit3, final String digit4) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, verify_url, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (new JSONObject(response).getString("type").equals("success")){
                                preferencesHelper.putBoolean("isLoggedIn",true);
                                Intent intent = new Intent(LoginOtpActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginOtpActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();
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
                params.put("mobile","91" + mobile);
                params.put("otp",digit1+digit2+digit3+digit4);
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
    public void checkOtp(View view) {
        verifyOtp(ed_digit1.getText().toString(),ed_digit2.getText().toString(),ed_digit3.getText().toString(),ed_digit4.getText().toString());
    }

}
