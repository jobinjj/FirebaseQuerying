package com.example.doctorbooking.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorbooking.R;
import com.example.imageutil.ImageManipulate;

public class ProfileActivity extends AppCompatActivity {

    private ImageView img_profile, btn_back;
    private TextView txt_name, txt_mail, txt_gender_age, txt_dob, txt_gender;
    private PreferencesHelper preferencesHelper;
    private ImageView btn_edit1;
    private ImageManipulate imageManipulate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageManipulate = new ImageManipulate(this);
        preferencesHelper = new PreferencesHelper(this);
        boolean hasProfile = preferencesHelper.getBoolean("hasProfile");
        img_profile = findViewById(R.id.img_profile);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_profile.setImageDrawable(getResources().getDrawable(R.drawable.ic_girl));
        btn_edit1 = findViewById(R.id.btn_edit);
        btn_edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
        if (hasProfile) {
            txt_name = findViewById(R.id.txt_name);
            txt_mail = findViewById(R.id.txt_mail);
            txt_gender_age = findViewById(R.id.txt_gender_age);
            txt_gender = findViewById(R.id.txt_gender);
            txt_dob = findViewById(R.id.txt_dob);
            btn_edit1 = findViewById(R.id.btn_edit);
            img_profile.setImageBitmap(imageManipulate.loadImageFromStorage(preferencesHelper.getString("profileimage")));
            txt_name.setText(preferencesHelper.getString("name"));
            txt_dob.setText(preferencesHelper.getString("dob"));
            txt_gender_age.setText(preferencesHelper.getString("age") + " Years");
            txt_gender.setText(preferencesHelper.getString("gender"));
            txt_mail.setText(preferencesHelper.getString("mail"));
        } else {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
