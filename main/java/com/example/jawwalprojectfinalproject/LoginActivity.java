package com.example.jawwalprojectfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email_et_login, password_et_login;
    TextView forgetPassword, signUp;
    Button mLoginBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d("registterTest", "onCreate: from login");

        email_et_login = findViewById(R.id.Email_et_login);
        password_et_login = findViewById(R.id.Password_et_login);
        mLoginBtn = findViewById(R.id.login_btn_loginPage);
        forgetPassword = findViewById(R.id.forgetPassword);
        signUp = findViewById(R.id.signUp_loginPage);

        auth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                Log.d("registterTest", "onClick: im here");
//                finish();
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_et_login.getText().toString();
                String password = password_et_login.getText().toString();

                if (TextUtils.isEmpty(email)){
                    email_et_login.setError("Email is required");
                    return;
                } if (TextUtils.isEmpty(password)){
                    password_et_login.setError("Password is required");
                    return;
                }
                if (password.length() < 6){
                    password_et_login.setError("Password must be more than 6 characters");
                    return;
                }
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else{
                            Toast.makeText(LoginActivity.this, "Error ! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}