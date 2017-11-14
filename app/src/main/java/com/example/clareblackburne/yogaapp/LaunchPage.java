package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LaunchPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch_page);
    }

    public void enter(View button){
        Intent intent = new Intent(this, TopSessionsActivity.class);
        startActivity(intent);
    }
}
