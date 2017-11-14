package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
        if(TextUtils.isEmpty(name)) {
            nameText.setError("Please add");
            return;
        }
        String day = dayText.getText().toString();
        if(TextUtils.isEmpty(day)) {
            dayText.setError("Please add");
            return;
        }
        String focus = focusText.getText().toString();
        if(TextUtils.isEmpty(focus)) {
            focusText.setError("Please add");
            return;
        }
        Integer duration = Integer.parseInt(durationText.getText().toString());
        if(TextUtils.isEmpty(duration.toString())){
            durationText.setError("Please add");
        }
        String status = "N";
        Session session = new Session(name, day, focus, duration, status);
        session.save(dbHelper);
        Intent intent = new Intent(this, TopSessionsActivity.class);
        startActivity(intent);
    }
}




