package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddSetActivity extends MainMenu implements OnItemSelectedListener {

    DBHelper dbHelper;
    Spinner spinner;
    Button addPoseToSessionButton;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        addPoseToSessionButton = (Button)findViewById(R.id.addPoseToSessionButton);
        loadPosesToSpinner();
    }


    public void loadPosesToSpinner(){

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Integer session_id = extras.getInt("id");
        String name = extras.getString("name");
        String day = extras.getString("day");
        String focus = extras.getString("focus");
        Integer duration = extras.getInt("duration");
        String status = extras.getString("status");
        session = new Session(session_id, name, day, focus, duration, status);

        DBHelper db = new DBHelper(this);
        ArrayList<Pose> poses = Pose.all(db);

        ArrayAdapter<Pose> adapter = new ArrayAdapter<Pose>(getApplicationContext(), android.R.layout.simple_spinner_item, poses);
        adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        db.close();
    }

    public void onClickAddPoseToSessionButton(View button) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Integer session_id = extras.getInt("id");
        int position = spinner.getSelectedItemPosition();
        Pose pose = (Pose) spinner.getItemAtPosition(position);
        Integer pose_id = pose.getId();

        dbHelper = new DBHelper(this);

        Set newSet = new Set(session_id, pose_id);
        newSet.save(dbHelper);


        Intent intent2  = new Intent(this, SessionActivity.class);
        intent2.putExtra("pose_id", pose_id);
        intent2.putExtra("id", session_id);
        startActivity(intent2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id){
        Pose pose = (Pose) parent.getItemAtPosition(position);
        Toast.makeText(parent.getContext(), "You've chosen to add: " + pose.getName(),
                Toast.LENGTH_LONG).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
//
   }


}
