package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by clareblackburne on 11/11/2017.
 */

public class AddSessionActivity extends MainMenu {

    EditText nameText;
    EditText dayText;
    EditText focusText;
    EditText durationText;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        nameText = (EditText)findViewById(R.id.nameText);
        dayText = (EditText)findViewById(R.id.dayText);
        focusText = (EditText)findViewById(R.id.focusText);
        durationText = (EditText)findViewById(R.id.durationText);
    }

    public void addSession(View button){
        dbHelper = new DBHelper(this);
        String name = nameText.getText().toString();
        String day = dayText.getText().toString();
        String focus = focusText.getText().toString();
        Integer duration = Integer.parseInt(durationText.getText().toString());
        String status = "N";
        Session session = new Session(name, day, focus, duration, status);
        session.save(dbHelper);
        Intent intent = new Intent(this, TopSessionsActivity.class);
        startActivity(intent);
    }
}




