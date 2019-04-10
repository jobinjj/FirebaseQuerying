package com.example.doctorbooking.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.doctorbooking.R;

public class DoctorlistActivity extends AppCompatActivity {

    private ImageView btn_book1,btn_book2,btn_book3,btn_book4;
    private ConstraintLayout constraintLayout4,constraintLayout3,constraintLayout2,constraintLayout5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlist);

        constraintLayout4 = findViewById(R.id.constraintLayout4);
        constraintLayout3 = findViewById(R.id.constraintLayout3);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        constraintLayout5 = findViewById(R.id.constraintLayout5);
        constraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorlistActivity.this,DoctorDetailActivity.class);
                intent.putExtra("image",R.drawable.ic_general_docnew_01);
                intent.putExtra("name","Dr. Mercy Tomson");
                intent.putExtra("role","Gynnecologist/Obsterician");
                startActivity(intent);
            }
        });
        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorlistActivity.this,DoctorDetailActivity.class);
                intent.putExtra("image",R.drawable.ic_doctor5);
                intent.putExtra("name","Dr Rathakrishnan");
                intent.putExtra("role","Cardiologists");
                startActivity(intent);
            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorlistActivity.this,DoctorDetailActivity.class);
                intent.putExtra("image",R.drawable.ic_doctor5);
                intent.putExtra("name","Dr Rathakrishnan");
                intent.putExtra("role","Cardiologists");
                startActivity(intent);
            }
        });

        btn_book1 = findViewById(R.id.btn_book1);
        btn_book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorlistActivity.this,DoctorDetailActivity.class);
                intent.putExtra("image",R.drawable.ic_general_docnew_01);
                intent.putExtra("name","Dr. Mercy Tomson");
                intent.putExtra("role","Gynnecologist/Obsterician");
                startActivity(intent);
            }
        });
        btn_book2 = findViewById(R.id.btn_book2);
        btn_book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorlistActivity.this,DoctorDetailActivity.class);
                intent.putExtra("image",R.drawable.ic_doctor5);
                intent.putExtra("name","Dr Rathakrishnan");
                intent.putExtra("role","Cardiologists");
                startActivity(intent);
            }
        });
        btn_book3 = findViewById(R.id.btn_book3);
        btn_book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorlistActivity.this,DoctorDetailActivity.class);
                intent.putExtra("image",R.drawable.ic_doctor6);
                intent.putExtra("name","Dr. George Thomas");
                intent.putExtra("role","Dermatologists");
                startActivity(intent);
            }
        });
        btn_book4 = findViewById(R.id.img_doctor42);
        btn_book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorlistActivity.this,DoctorDetailActivity.class);
                intent.putExtra("image",R.drawable.ic_doctor4);
                intent.putExtra("name","Dr. A K Nambiar");
                intent.putExtra("role","Endocrinologists");
                startActivity(intent);
            }
        });

    }
}
