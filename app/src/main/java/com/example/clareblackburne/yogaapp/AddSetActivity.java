package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_CHAKRA;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_DURATION;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_ID;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_IMAGE;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_SANSKRITNAME;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_TABLE_NAME;

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
        loadPosesToSpinner();} //needs an override to display names only



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
        ArrayList<Pose> poses = session.findAllPoses(db);

        ArrayAdapter<Pose> adapter = new ArrayAdapter<Pose>(getApplicationContext(), android.R.layout.simple_spinner_item, poses);
        adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onClickAddPoseToSessionButton(View button){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Integer session_id = extras.getInt("id");
        int position = spinner.getSelectedItemPosition();
        Pose pose = (Pose) spinner.getItemAtPosition(position);
        Integer pose_id = pose.getId();

        dbHelper = new DBHelper(this);
        dbHelper = new DBHelper(this);

        Set set = session.getSet(dbHelper);
        if(set == null){
            Set newSet = new Set(session_id, pose_id);
            newSet.save(dbHelper);}
        else {
        set.update(session_id, pose_id, dbHelper);}


//        int set_id = set.getId();
        Intent intent2  = new Intent(this, SessionActivity.class);
        intent2.putExtra("pose_id", pose_id);
        intent2.putExtra("id", session_id);
//        intent2.putExtra("set_id", set_id);
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

    public ArrayList<Pose> getPoses(DBHelper dbHelper){ //check this function works or is being used
        ArrayList<Pose> poses = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + POSES_TABLE_NAME;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null );
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(POSES_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(POSES_COLUMN_IMAGE));
            String sanskritName = cursor.getString(cursor.getColumnIndex(POSES_COLUMN_SANSKRITNAME));
            String chakra = cursor.getString(cursor.getColumnIndex(POSES_COLUMN_CHAKRA));
            Integer duration = cursor.getInt(cursor.getColumnIndex(POSES_COLUMN_DURATION));
            Integer image = cursor.getInt(cursor.getColumnIndex(POSES_COLUMN_IMAGE));
            Pose pose = new Pose(id, name, sanskritName, chakra, duration, image);
            poses.add(pose);
        }
        cursor.close();
        db.close();
        return poses;
    }

}
