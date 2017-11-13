package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PoseActivity extends MainMenu {

    TextView namePose;
    TextView sanskritPose;
    TextView durationPose;
    TextView chakraPose;
    ImageView poseImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pose);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        String sanskritName = extras.getString("sanskritName");
        String chakra = extras.getString("chakra");
        Integer duration = extras.getInt("duration");
        Integer image = extras.getInt("image");

        namePose = (TextView) findViewById(R.id.namePose);
        namePose.setText(name);

        sanskritPose = (TextView) findViewById(R.id.sanskritPose);
        sanskritPose.setText(sanskritName);

        durationPose = (TextView) findViewById(R.id.durationPose);
        durationPose.setText(duration.toString());

        chakraPose = (TextView) findViewById(R.id.chakraPose);
        chakraPose.setText(chakra);

        poseImage = (ImageView) findViewById(R.id.poseImage);
        poseImage.setImageResource(image);


    }

//
//    public void editPose(View button){
//
//    }


    public void deletePose(View button){
        if(button.getId() == R.id.deletePoseButton){
        Bundle extras = getIntent().getExtras();
        Integer id = extras.getInt("id");
        DBHelper dbHelper = new DBHelper(this);
        Pose.delete(dbHelper, id);
        Intent intent = new Intent(this, TopPosesActivity.class);
        startActivity(intent);}
    }
}
