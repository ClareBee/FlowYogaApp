package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.clareblackburne.yogaapp.DBHelper.SET_TABLE_NAME;

public class SessionActivity extends MainMenu {


    TextView nameSession;
    TextView daySession;
    TextView focusSession;
    TextView durationSession;
    Button quotation;
    Button addPoseToSession;
    ImageView sessionImageView;
    DBHelper dbHelper;
    ListView setList2;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        dbHelper = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        Integer session_id = extras.getInt("id");

        session = dbHelper.getSessionById(session_id);

        nameSession = (TextView)findViewById(R.id.nameSession);
        nameSession.setText(session.getName());

        daySession = (TextView)findViewById(R.id.daySession);
        daySession.setText(session.getDay());

        focusSession = (TextView)findViewById(R.id.focusSession);
        focusSession.setText(session.getFocus());

        durationSession = (TextView)findViewById(R.id.durationSession);
        durationSession.setText(session.getDuration().toString());

        setList2 = (ListView)findViewById(R.id.setList);

        quotation = (Button)findViewById(R.id.quotation);

        addPoseToSession = (Button)findViewById(R.id.addPoseToSession);

        sessionImageView = (ImageView)findViewById(R.id.sessionImageView);
        sessionImageView.setImageResource(R.drawable.lotus);


        Set set = session.getSet(dbHelper);
        if(set != null) {

            ArrayList<Pose> posesInSetList = set.allPosesInSet(session_id, dbHelper);
            SetAdapter adapter = new SetAdapter(this, posesInSetList);
            setList2.setAdapter(adapter);
        }
    }

    //how to get this to work re: setList2 ListView if you click on the listItem you should be able to access the pose details
    public void getPoseInSet(View listItem){

        Pose pose = (Pose) listItem.getTag();
        Intent intent3 = new Intent(this, PoseActivity.class);
        intent3.putExtra("name", pose.getName());
        intent3.putExtra("sanskritName", pose.getSanskritName());
        intent3.putExtra("chakra", pose.getChakra());
        intent3.putExtra("duration", pose.getDuration());
        intent3.putExtra("image", pose.getImage());
        intent3.putExtra("id", pose.getId());
        startActivity(intent3);
    }


// do i have to delete the related set details before i can delete the session?? YES!!! How?
//insert button NB
//    public void deleteSet(View button){
//        if(button.getId() == R.id.deleteSetButton) {
//            Bundle extras = getIntent().getExtras();
//            Integer id = extras.getInt("set_id");
//            DBHelper db2 = new DBHelper(this);
//            this.deleteSetById(db2, id);
//        }
//    }

    public void deleteSession(View button){
        Bundle extras = getIntent().getExtras();
        Integer id = extras.getInt("id");
        DBHelper dbHelper = new DBHelper(this);
        Session.delete(dbHelper, id);
        Intent intent = new Intent(this, TopSessionsActivity.class);
        startActivity(intent);
    }

    public void getQuotation(View button){
        Intent intent = new Intent(this, QuotationActivity.class);
        startActivity(intent);
    }

    public void addPoseToThisSession(View button){
        if(button.getId() == R.id.addPoseToSession){
        Intent intent = new Intent(this, AddSetActivity.class);
            intent.putExtra("id", session.getId());
            intent.putExtra("name", session.getName());
            intent.putExtra("day", session.getDay());
            intent.putExtra("focus", session.getDay());
            intent.putExtra("duration", session.getDuration());
            intent.putExtra("status", session.getStatus());
        startActivity(intent);}
    }


    public void completeThisSession(View button){
        if(button.getId() == R.id.completeButton){
            button.setBackgroundColor(Color.DKGRAY);
            DBHelper thisdb = new DBHelper(this);
            session.updateAsComplete(thisdb);
        }
    }

    public void doSessionAgain(View button){
        if(button.getId() == R.id.redoButton){
            DBHelper anotherdb = new DBHelper(this);
            session.redoSession(anotherdb);
        }
    }



    public boolean deleteSetById(DBHelper dbHelper, Integer id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = " id = ?";
        String[] values = {id.toString()};
        db.delete(SET_TABLE_NAME, selection, values);
        return true;
    }


}
