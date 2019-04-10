package com.example.doctorbooking.main;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.doctorbooking.R;
import com.example.imageutil.ImageManipulate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EditProfileActivity extends AppCompatActivity {

    private EditText ed_name, ed_email, ed_age;
    private ImageView img_dob;
    private String gender = "null";
    private ImageView btn_submit;
    private String date;
    private TextView txt_dob;
    private ImageView imageView9;
    private ImageManipulate imageManipulate;
    private String imagepath;
    private PreferencesHelper preferencesHelper;
    private Uri selectedImage;
    private ProgressDialog progressDialog;
    private Bitmap bitmap;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        progressDialog = new ProgressDialog(this);
        progressBar = findViewById(R.id.progressBar);
       // progressDialog.setCanceledOnTouchOutside(false);

        imageManipulate = new ImageManipulate(this);
        preferencesHelper = new PreferencesHelper(EditProfileActivity.this);
        imageView9 = findViewById(R.id.imageView9);
        txt_dob = findViewById(R.id.txt_dob2);
        ed_name = findViewById(R.id.ed_name);
        ed_email = findViewById(R.id.ed_email);
        ed_age = findViewById(R.id.ed_age);
        img_dob = findViewById(R.id.img_dob);
        btn_submit = findViewById(R.id.btn_submit);
        if (preferencesHelper.getBoolean("hasProfile"))
        imageView9.setImageBitmap(imageManipulate.loadImageFromStorage(preferencesHelper.getString("profileimage")));

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
        ed_name.setText(preferencesHelper.getString("username"));
        ed_email.setText(preferencesHelper.getString("mail"));
        if (!preferencesHelper.getString("dob").equals(""))
        txt_dob.setText(preferencesHelper.getString("dob"));
        gender = preferencesHelper.getString("gender");
        date = preferencesHelper.getString("dob");

        if (preferencesHelper.getString("gender").equals("")) {
            String agetest = preferencesHelper.getString("age");
        } else if (preferencesHelper.getString("gender").equals("Male")) {
            RadioButton radio_male = findViewById(R.id.radio_male);
            radio_male.setChecked(true);
        } else if (preferencesHelper.getString("gender").equals("Female")) {
            RadioButton radio_female = findViewById(R.id.radio_female);
            radio_female.setChecked(true);
        }
        ed_age.setText(preferencesHelper.getString("age"));
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesHelper.putString("name", ed_name.getText().toString());
                preferencesHelper.putString("mail", ed_email.getText().toString());
                preferencesHelper.putString("age", ed_age.getText().toString());
                preferencesHelper.putString("gender", gender);
                preferencesHelper.putString("dob", date);
                preferencesHelper.putBoolean("hasProfile", true);
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        img_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Calendar cal = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                date = df.format(cal.getTime());
                                txt_dob.setText(date);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_male:
                if (checked)
                    gender = "Male";
                break;
            case R.id.radio_female:
                if (checked)
                    gender = "Female";
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1) {
            if (data != null){
                selectedImage = data.getData();
                BitmapTask bitmapTask = new BitmapTask();
                bitmapTask.execute(selectedImage);
            }
            //TODO: action
        }
    }
    public class BitmapTask extends AsyncTask<Uri, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Uri... uris) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(EditProfileActivity.this.getContentResolver(), selectedImage);
                ContextWrapper cw = new ContextWrapper(EditProfileActivity.this);
                // path to /data/data/yourapp/app_data/imageDir
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                // Create imageDir
                File mypath=new File(directory,"profile.jpg");

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mypath);
                    // Use the compress method on the BitMap object to write image to the OutputStream
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                imagepath = directory.getAbsolutePath();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return imagepath;
        }

        @Override
        protected void onPostExecute(String path) {
            super.onPostExecute(path);
            progressBar.setVisibility(View.GONE);
            preferencesHelper.putString("profileimage",imagepath);
            imageView9.setImageBitmap(bitmap);

        }
    }
}
