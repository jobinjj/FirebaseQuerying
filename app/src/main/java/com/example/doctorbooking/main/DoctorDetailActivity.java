package com.example.doctorbooking.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doctorbooking.R;

public class DoctorDetailActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{
    ImageView imageView8,imageView;
    TextView textView18,textView;

    private ConstraintLayout sat_morning_slot1,sat_morning_slot2,sat_morning_slot3,sat_morning_slot4,sat_evening_slot1,sat_evening_slot2,sat_evening_slot3,sat_evening_slot4;
    private ConstraintLayout fri_morning_slot1,fri_morning_slot2,fri_morning_slot3,fri_morning_slot4,fri_evening_slot1,fri_evening_slot2,fri_evening_slot3,fri_evening_slot4;
    private TextView sat_txt_morning_slot1,sat_txt_morning_slot2,sat_txt_morning_slot3,sat_txt_morning_slot4,sat_txt_evening_slot1,sat_txt_evening_slot2,sat_txt_evening_slot3,sat_txt_evening_slot4;
    private TextView fri_txt_morning_slot1,fri_txt_morning_slot2,fri_txt_morning_slot3,fri_txt_morning_slot4,fri_txt_evening_slot1,fri_txt_evening_slot2,fri_txt_evening_slot3,fri_txt_evening_slot4;
    private ConstraintLayout previous_fri_slot;
    private ConstraintLayout previous_sat_slot;
    private TextView previous_fri_text;
    private TextView previous_sat_text;
    private String spinner_item;
    private Button btn_confirm;
    private String name;
    private String role;
    private int image;
    private TextView textView2;
    private String selectedDate;
    private String selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        init();
        fridayOnClick();
        saturdayOnClick();
        spinner();
    }

    private void spinner() {
        Spinner spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.slots_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    private void saturdayOnClick() {
        sat_morning_slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Sat 30,19";
                selectedTime = sat_txt_morning_slot1.getText().toString();
                if (previous_sat_slot != null && previous_sat_text != null){
                    previous_sat_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_sat_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                sat_txt_morning_slot1.setTextColor(getResources().getColor(R.color.white));
                sat_morning_slot1.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_sat_slot = sat_morning_slot1;
                previous_sat_text = sat_txt_morning_slot1;
                btn_confirm.setEnabled(true);
            }
        });
        sat_morning_slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Sat 30,19";
                selectedTime = sat_txt_morning_slot2.getText().toString();
                if (previous_sat_slot != null && previous_sat_text != null){
                    previous_sat_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_sat_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                sat_txt_morning_slot2.setTextColor(getResources().getColor(R.color.white));
                sat_morning_slot2.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_sat_slot = sat_morning_slot2;
                previous_sat_text = sat_txt_morning_slot2;
                btn_confirm.setEnabled(true);
            }
        });
        sat_morning_slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedDate = "Sat 30,19";
                selectedTime = sat_txt_morning_slot3.getText().toString();
                if (previous_sat_slot != null && previous_sat_text != null){
                    previous_sat_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_sat_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                sat_txt_morning_slot3.setTextColor(getResources().getColor(R.color.white));
                sat_morning_slot3.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_sat_slot = sat_morning_slot3;
                previous_sat_text = sat_txt_morning_slot3;
                btn_confirm.setEnabled(true);
            }
        });
        sat_morning_slot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedDate = "Sat 30,19";
                selectedTime = sat_txt_morning_slot4.getText().toString();
                if (previous_sat_slot != null && previous_sat_text != null){
                    previous_sat_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_sat_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                sat_txt_morning_slot4.setTextColor(getResources().getColor(R.color.white));
                sat_morning_slot4.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_sat_slot = sat_morning_slot4;
                previous_sat_text = sat_txt_morning_slot4;
                btn_confirm.setEnabled(true);
            }
        });
        sat_evening_slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedDate = "Sat 30,19";
                selectedTime = sat_txt_evening_slot1.getText().toString();
                if (previous_sat_slot != null && previous_sat_text != null){
                    previous_sat_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_sat_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                sat_txt_evening_slot1.setTextColor(getResources().getColor(R.color.white));
                sat_evening_slot1.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_sat_slot = sat_evening_slot1;
                previous_sat_text = sat_txt_evening_slot1;
                btn_confirm.setEnabled(true);
            }
        });
        sat_evening_slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Sat 30,19";
                selectedTime = sat_txt_evening_slot2.getText().toString();
                if (previous_sat_slot != null && previous_sat_text != null){
                    previous_sat_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_sat_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                sat_txt_evening_slot2.setTextColor(getResources().getColor(R.color.white));
                sat_evening_slot2.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_sat_slot = sat_evening_slot2;
                previous_sat_text = sat_txt_evening_slot2;
                btn_confirm.setEnabled(true);
            }
        });
        sat_evening_slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Sat 30,19";
                selectedTime = sat_txt_evening_slot3.getText().toString();
                if (previous_sat_slot != null && previous_sat_text != null){
                    previous_sat_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_sat_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                sat_txt_evening_slot3.setTextColor(getResources().getColor(R.color.white));
                sat_evening_slot3.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_sat_slot = sat_evening_slot3;
                previous_sat_text = sat_txt_evening_slot3;
                btn_confirm.setEnabled(true);
            }
        });
        sat_evening_slot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Sat 30,19";
                selectedTime = sat_txt_evening_slot4.getText().toString();
                if (previous_sat_slot != null && previous_sat_text != null){
                    previous_sat_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_sat_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                sat_txt_evening_slot4.setTextColor(getResources().getColor(R.color.white));
                sat_evening_slot4.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_sat_slot = sat_evening_slot4;
                previous_sat_text = sat_txt_evening_slot4;
                btn_confirm.setEnabled(true);
            }
        });
    }

    private void fridayOnClick() {
//        fri_morning_slot1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (previous_fri_slot != null && previous_fri_text != null){
//                    previous_fri_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
//                    previous_fri_text.setTextColor(getResources().getColor(R.color.normal));
//                }
//                fri_txt_morning_slot1.setTextColor(getResources().getColor(R.color.white));
//                fri_morning_slot1.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
//                previous_fri_slot = fri_morning_slot1;
//                previous_fri_text = fri_txt_morning_slot1;
//            }
//        });
        fri_morning_slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Fri 29,19";
                selectedTime = fri_txt_morning_slot2.getText().toString();
                if (previous_fri_slot != null && previous_fri_text != null){
                    previous_fri_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_fri_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                fri_txt_morning_slot2.setTextColor(getResources().getColor(R.color.white));
                fri_morning_slot2.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_fri_slot = fri_morning_slot2;
                previous_fri_text = fri_txt_morning_slot2;
                btn_confirm.setEnabled(true);
            }
        });
        fri_morning_slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Fri 29,19";
                selectedTime = fri_txt_morning_slot3.getText().toString();
                if (previous_fri_slot != null && previous_fri_text != null){
                    previous_fri_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_fri_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                fri_txt_morning_slot3.setTextColor(getResources().getColor(R.color.white));
                fri_morning_slot3.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_fri_slot = fri_morning_slot3;
                previous_fri_text = fri_txt_morning_slot3;
                btn_confirm.setEnabled(true);
            }
        });
        fri_morning_slot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Fri 29,19";
                selectedTime = fri_txt_morning_slot4.getText().toString();
                if (previous_fri_slot != null && previous_fri_text != null){
                    previous_fri_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_fri_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                fri_txt_morning_slot4.setTextColor(getResources().getColor(R.color.white));
                fri_morning_slot4.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_fri_slot = fri_morning_slot4;
                previous_fri_text = fri_txt_morning_slot4;
                btn_confirm.setEnabled(true);
            }
        });
        fri_evening_slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Fri 29,19";
                selectedTime = fri_txt_evening_slot1.getText().toString();
                if (previous_fri_slot != null && previous_fri_text != null){
                    previous_fri_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_fri_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                fri_txt_evening_slot1.setTextColor(getResources().getColor(R.color.white));
                fri_evening_slot1.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_fri_slot = fri_evening_slot1;
                previous_fri_text = fri_txt_evening_slot1;
                btn_confirm.setEnabled(true);
            }
        });
        fri_evening_slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Fri 29,19";
                selectedTime = fri_txt_evening_slot2.getText().toString();
                if (previous_fri_slot != null && previous_fri_text != null){
                    previous_fri_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_fri_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                fri_txt_evening_slot2.setTextColor(getResources().getColor(R.color.white));
                fri_evening_slot2.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_fri_slot = fri_evening_slot2;
                previous_fri_text = fri_txt_evening_slot2;
                btn_confirm.setEnabled(true);
            }
        });
        fri_evening_slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Fri 29,19";
                selectedTime = fri_txt_evening_slot3.getText().toString();
                if (previous_fri_slot != null && previous_fri_text != null){
                    previous_fri_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_fri_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                fri_txt_evening_slot3.setTextColor(getResources().getColor(R.color.white));
                fri_evening_slot3.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_fri_slot = fri_evening_slot3;
                previous_fri_text = fri_txt_evening_slot3;
                btn_confirm.setEnabled(true);
            }
        });
        fri_evening_slot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "Fri 29,19";
                selectedTime = fri_txt_evening_slot4.getText().toString();
                if (previous_fri_slot != null && previous_fri_text != null){
                    previous_fri_slot.setBackground(getResources().getDrawable(R.drawable.slot_bg));
                    previous_fri_text.setTextColor(getResources().getColor(R.color.normal));
                    btn_confirm.setEnabled(true);
                }
                fri_txt_evening_slot4.setTextColor(getResources().getColor(R.color.white));
                fri_evening_slot4.setBackground(getResources().getDrawable(R.drawable.slot_bg_selected));
                previous_fri_slot = fri_evening_slot4;
                previous_fri_text = fri_txt_evening_slot4;
                btn_confirm.setEnabled(true);
            }
        });

    }

    private void init() {
        name = getIntent().getStringExtra("name");
        role = getIntent().getStringExtra("role");
        image = getIntent().getIntExtra("image",0);
        btn_confirm = findViewById(R.id.button);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FireMissilesDialogFragment fireMissilesDialogFragment = new FireMissilesDialogFragment().newInstance(image,name,role);
//
//                fireMissilesDialogFragment.show(getSupportFragmentManager(), "dialog");
                ConfirmDialog confirmDialog = new ConfirmDialog(DoctorDetailActivity.this,name,selectedDate,selectedTime,image);
                confirmDialog.show();
            }
        });
        textView18 = findViewById(R.id.textView18);
        textView2 = findViewById(R.id.textView2);
        textView = findViewById(R.id.textView);
        imageView8 = findViewById(R.id.imageView8);
        imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(image));
        textView.setText(name);
        textView2.setText(role);



        sat_morning_slot1 = findViewById(R.id.sat_morning_slot1);
        sat_morning_slot2 = findViewById(R.id.sat_morning_slot2);
        sat_morning_slot3 = findViewById(R.id.sat_morning_slot3);
        sat_morning_slot4 = findViewById(R.id.sat_morning_slot4);
        sat_evening_slot1 = findViewById(R.id.sat_evening_slot1);
        sat_evening_slot2 = findViewById(R.id.sat_evening_slot2);
        sat_evening_slot3 = findViewById(R.id.sat_evening_slot3);
        sat_evening_slot4 = findViewById(R.id.sat_evening_slot4);

        fri_morning_slot1 = findViewById(R.id.fri_morning_slot1);
        fri_morning_slot2 = findViewById(R.id.fri_morning_slot2);
        fri_morning_slot3 = findViewById(R.id.fri_morning_slot3);
        fri_morning_slot4 = findViewById(R.id.fri_morning_slot4);
        fri_evening_slot1 = findViewById(R.id.fri_evening_slot1);
        fri_evening_slot2 = findViewById(R.id.fri_evening_slot2);
        fri_evening_slot3 = findViewById(R.id.fri_evening_slot3);
        fri_evening_slot4 = findViewById(R.id.fri_evening_slot4);


        sat_txt_morning_slot1 = findViewById(R.id.sat_txt_morning_slot1);
        sat_txt_morning_slot2 = findViewById(R.id.sat_txt_morning_slot2);
        sat_txt_morning_slot3 = findViewById(R.id.sat_txt_morning_slot3);
        sat_txt_morning_slot4 = findViewById(R.id.sat_txt_morning_slot4);
        sat_txt_evening_slot1 = findViewById(R.id.sat_txt_evening_slot1);
        sat_txt_evening_slot2 = findViewById(R.id.sat_txt_evening_slot2);
        sat_txt_evening_slot3 = findViewById(R.id.sat_txt_evening_slot3);
        sat_txt_evening_slot4 = findViewById(R.id.sat_txt_evening_slot4);

        fri_txt_morning_slot1 = findViewById(R.id.fri_txt_morning_slot1);
        fri_txt_morning_slot2 = findViewById(R.id.fri_txt_morning_slot2);
        fri_txt_morning_slot3 = findViewById(R.id.fri_txt_morning_slot3);
        fri_txt_morning_slot4 = findViewById(R.id.fri_txt_morning_slot4);
        fri_txt_evening_slot1 = findViewById(R.id.fri_txt_evening_slot1);
        fri_txt_evening_slot2 = findViewById(R.id.fri_txt_evening_slot2);
        fri_txt_evening_slot3 = findViewById(R.id.fri_txt_evening_slot3);
        fri_txt_evening_slot4 = findViewById(R.id.fri_txt_evening_slot4);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner_item = parent.getItemAtPosition(position).toString();
        switch (spinner_item){
            case "sat 30,19":
                findViewById(R.id.container_friday).setVisibility(View.GONE);
                findViewById(R.id.container_saturday).setVisibility(View.VISIBLE);
                imageView8.setVisibility(View.GONE);
                textView18.setVisibility(View.GONE);
                break;
            case "Fri 29,19":
                findViewById(R.id.container_friday).setVisibility(View.VISIBLE);
                findViewById(R.id.container_saturday).setVisibility(View.GONE);
                imageView8.setVisibility(View.GONE);
                textView18.setVisibility(View.GONE);
                break;
            case "Select date":
                findViewById(R.id.container_friday).setVisibility(View.GONE);
                findViewById(R.id.container_saturday).setVisibility(View.GONE);
                break;
        }
        if (spinner_item.equals("sat 30,19")){
        }else if (spinner_item.equals("Fri 29,19")){
        }else if (spinner_item.equals("Select date")){
        }
    }

//    public static class FireMissilesDialogFragment extends DialogFragment {
//
//        private int image;
//        private String name,role;
//
//        public static FireMissilesDialogFragment newInstance(int image, String name, String role) {
//            FireMissilesDialogFragment f = new FireMissilesDialogFragment();
//
//
//            // Supply image input as an argument.
//            Bundle args = new Bundle();
//            args.putInt("image", image);
//            args.putString("name", name);
//            args.putString("role", role);
//
//            f.setArguments(args);
//
//            return f;
//        }
//
//        @Override
//        public void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//        }
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Use the Builder class for convenient dialog construction
//            image = getArguments().getInt("image");
//            name = getArguments().getString("name");
//            role = getArguments().getString("role");
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setMessage(R.string.dialog_fire_missiles)
//                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            Intent intent = new Intent(getActivity(),FinalActivity.class);
//                            intent.putExtra("name",name);
//                            intent.putExtra("image",image);
//                            intent.putExtra("role",role);
//                            startActivity(intent);
//                            getActivity().finish();
//                        }
//                    })
//                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//
//                        }
//                    });
//            // Create the AlertDialog object and return it
//            return builder.create();
//        }
//
//    }

    public class ConfirmDialog extends Dialog {
        private final Activity activity;
        private  String name,date_booked,time_booked;
        private int image;
        private ImageView img_doctor;
        private Button btn_yes;
        private Button btn_no;

        public ConfirmDialog(Activity activity,String name,String date_booked,String time_booked,int image){
            super(activity);
            this.activity = activity;
            this.name = name;
            this.date_booked = date_booked;
            this.time_booked = time_booked;
            this.image = image;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.confirm_dialog);

            btn_yes = findViewById(R.id.btn_yes);
            btn_no= findViewById(R.id.btn_no);
            img_doctor = findViewById(R.id.img_doctor);
            TextView txt_doctorname = findViewById(R.id.txt_doctorname);
            TextView txt_date = findViewById(R.id.txt_date);
            TextView txt_time = findViewById(R.id.txt_time);
            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,FinalActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("image",image);
                    intent.putExtra("role",role);
                    intent.putExtra("date",date_booked);
                    intent.putExtra("time",time_booked);
                    startActivity(intent);
                    finish();
                    dismiss();
                }
            });

            img_doctor.setImageDrawable(getResources().getDrawable(image));
            txt_doctorname.setText(name);
            txt_date.setText("Date : " + date_booked);
            txt_time.setText("Time : " + time_booked);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
