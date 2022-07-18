package com.example.sportbet.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportbet.view.error.ExceptionHandler;

public abstract class OnCreateClass extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        onSpecificCreate();
    }

    protected abstract void onSpecificCreate();
}
