package com.example.second;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_Second_SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}



