package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


/**
 * Created by clareblackburne on 11/11/2017.
 */

public class AddPoseActivity extends MainMenu{

    EditText nameInput;
    EditText sanskritNameInput;
    EditText chakraInput;
    EditText durationInput;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_pose);

        nameInput = (EditText)findViewById(R.id.nameInput);
        sanskritNameInput = (EditText)findViewById(R.id.sanskritNameInput);
        chakraInput = (EditText)findViewById(R.id.chakraInput);
        durationInput = (EditText)findViewById(R.id.durationInput);
    }

    public void addPose(View button){
        dbHelper = new DBHelper(this);

        String name = nameInput.getText().toString();
        String sanskritName = sanskritNameInput.getText().toString();
        String chakra = chakraInput.getText().toString();
        Integer duration = Integer.parseInt(durationInput.getText().toString());
        Integer image = R.drawable.lotus;

        Pose pose = new Pose(name, sanskritName, chakra, duration, image);
        pose.save(dbHelper);

        Intent intent = new Intent(this, TopPosesActivity.class);
        startActivity(intent);
    }


}




