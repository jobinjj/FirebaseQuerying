package com.example.doctorbooking.main;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doctorbooking.R;
import com.example.doctorbooking.model.FirestoreModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class LoginFirebaseTestActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText ed_pass,ed_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase_test);
        ed_mobile = findViewById(R.id.ed_mobile);
        ed_pass= findViewById(R.id.ed_pass);

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("DoctorBooking").document(ed_mobile.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    FirestoreModel user = Objects.requireNonNull(task.getResult()).toObject(FirestoreModel.class);
                                    //Navigate to profile page
                                    if(user != null){
                                        Log.d("tag",user.getMobile());
                                        if (ed_pass.getText().toString().equals(user.getPassword())){
                                            Toast.makeText(LoginFirebaseTestActivity.this, "Passwords match", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(LoginFirebaseTestActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(LoginFirebaseTestActivity.this, "Missing user record ", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(LoginFirebaseTestActivity.this, "user does not exist", Toast.LENGTH_SHORT).show();
                    }
                });
//                db.collection("DoctorBooking").whereEqualTo("password",ed_pass.getText().toString())
//                       .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()){
//                                    FirestoreModel user = Objects.requireNonNull(task.getResult()).toObject(FirestoreUserModel.class);
//                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                        Log.d("tag", document.getId() + " => " + document.getData());
//                                    }
//                                }else {
//                                    Log.d("tag",task.getResult().toString());
//                                    Toast.makeText(LoginFirebaseTestActivity.this, "Error ", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
            }
        });
    }
}
