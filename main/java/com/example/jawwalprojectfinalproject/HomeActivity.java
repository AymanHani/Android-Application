package com.example.jawwalprojectfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ImageView imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageSlider = findViewById(R.id.myimage);

    }
}