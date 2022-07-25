package com.example.jawwalprojectfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText Fullname_et, email_et, password_et;
    CheckBox checkBoxAgree;
    Button Btn_Create;
    FirebaseAuth auth;
    TextView signUp_register;
    ImageView previous_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.d("registterTest", "onCreate: from Register");

        Fullname_et = findViewById(R.id.name_et_registration);
        email_et = findViewById(R.id.Email_et_registration);
        password_et = findViewById(R.id.password_et_registration);
        checkBoxAgree = findViewById(R.id.checkBox_registration);
        Btn_Create = findViewById(R.id.Create_btn_registration);
        signUp_register = findViewById(R.id.signUp_register);
        previous_icon = findViewById(R.id.previous_icon);

        signUp_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        previous_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
        auth = FirebaseAuth.getInstance();


        // TODO: 7/21/2022 In home Page login
//        if (auth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            finish();
//        }

        Btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_et.getText().toString();
                String password = password_et.getText().toString();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                if (TextUtils.isEmpty(email)){
                    email_et.setError("Email is required");
                    return;
                }if (TextUtils.isEmpty(password)){
                    password_et.setError("Password is required");
                    return;
                }if (password.length() < 6){
                    password_et.setError("Password must be more than 6 characters");
                }
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error ! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}