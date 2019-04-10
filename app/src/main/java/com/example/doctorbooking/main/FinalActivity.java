package com.example.doctorbooking.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorbooking.R;

public class FinalActivity extends AppCompatActivity {

    private static final DecelerateInterpolator DecelerateInterpolator = new DecelerateInterpolator();
    private static final BounceInterpolator bounce = new BounceInterpolator();
    private int image;
    private String name;
    private String role,date,time;
    private ImageView imageView3;
    private TextView textView2;
    private TextView textView15, textView;
    private ConstraintLayout confirm_msg;
    private LinearLayout loadmore_divider;
    private TextView txt_booked_date;
    private TextView txt_booked_date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        image = getIntent().getIntExtra("image", 0);
        name = getIntent().getStringExtra("name");
        role = getIntent().getStringExtra("role");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");


        ImageView button2 = findViewById(R.id.button2);

        imageView3 = findViewById(R.id.imageView3);
        txt_booked_date = findViewById(R.id.txt_booked_date);
        txt_booked_date2 = findViewById(R.id.txt_booked_date2);
        txt_booked_date.setText(date);
        txt_booked_date2.setText(time);
        textView15 = findViewById(R.id.textView15);
        textView2 = findViewById(R.id.textView2);
        textView = findViewById(R.id.textView);
        imageView3.setImageDrawable(getResources().getDrawable(image));
        textView.setText(name);
        textView2.setText(role);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
                TransitionManager.beginDelayedTransition(transitionsContainer);
                confirm_msg = findViewById(R.id.confirm_msg);
                confirm_msg.setVisibility(View.GONE);
            }
        }, 1000);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FinalActivity.this, "reservation cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        textView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FinalActivity.this, "reservation cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}
