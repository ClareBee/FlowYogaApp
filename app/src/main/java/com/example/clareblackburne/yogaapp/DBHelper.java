package com.example.clareblackburne.yogaapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by clareblackburne on 11/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "yogaapp.db";

    public static final String SESSIONS_TABLE_NAME = "sessions";
    public static final String SESSIONS_COLUMN_ID = "id";
    public static final String SESSIONS_COLUMN_NAME = "name";
    public static final String SESSIONS_COLUMN_DAY = "day";
    public static final String SESSIONS_COLUMN_FOCUS = "focus";
    public static final String SESSIONS_COLUMN_DURATION = "duration";
    public static final String SESSIONS_COLUMN_STATUS = "status";

    public static final String POSES_TABLE_NAME = "poses";
    public static final String POSES_COLUMN_ID = "id";
    public static final String POSES_COLUMN_NAME = "name";
    public static final String POSES_COLUMN_SANSKRITNAME = "sanskritName";
    public static final String POSES_COLUMN_CHAKRA = "chakra";
    public static final String POSES_COLUMN_DURATION = "duration";
    public static final String POSES_COLUMN_IMAGE = "image";


    public static final String SET_TABLE_NAME = "sessions_poses";
    public static final String SET_COLUMN_ID = "id";
    public static final String SET_SESSION_ID = "session_id";
    public static final String SET_POSES_ID = "poses_id";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 4);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + SESSIONS_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT NOT NULL, day TEXT NOT NULL, focus TEXT, duration INTEGER, status TEXT );");
        db.execSQL("CREATE TABLE " + POSES_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT, sanskritName TEXT, chakra TEXT, duration INTEGER, image INTEGER );");
        db.execSQL("CREATE TABLE " + SET_TABLE_NAME + "(id INTEGER primary key autoincrement, session_id INTEGER, poses_id INTEGER );");


        //seed tables

        db.execSQL("INSERT INTO " + SESSIONS_TABLE_NAME + " (name, day, focus, duration, status) VALUES ('Session 1', 'Monday', 'Legs', 60, 'N');");
        db.execSQL("INSERT INTO " + POSES_TABLE_NAME + " (name, sanskritName, chakra, duration, image) VALUES ('Bridge', 'Setu Bandha Sarvangasasana', 'Root Heart Throat', 1, " +  R.drawable.bridge + ");");

        //how to insert values?
//        db.execSQL("INSERT INTO " + SET_TABLE_NAME + "(session_id, poses_id) VALUES (1, 1);");


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w("new", "Database has changed: " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + SESSIONS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + POSES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SET_TABLE_NAME);
        onCreate(db);
    }




    public Session getSessionById(Integer thisSessionId){
        ArrayList<Session> sessions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SESSIONS_TABLE_NAME + " WHERE id = " + thisSessionId.toString(), null);
        if(cursor != null && cursor.getCount() > 0){
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(SESSIONS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(SESSIONS_COLUMN_NAME));
            String day = cursor.getString(cursor.getColumnIndex(SESSIONS_COLUMN_DAY));
            String focus = cursor.getString(cursor.getColumnIndex(SESSIONS_COLUMN_FOCUS));
            Integer duration = cursor.getInt(cursor.getColumnIndex(SESSIONS_COLUMN_DURATION));
            String status = cursor.getString(cursor.getColumnIndex(SESSIONS_COLUMN_STATUS));
            Session session = new Session(id, name, day, focus, duration, status);
            sessions.add(session);
        }}
        else { return null;
        }
        cursor.close();
        return sessions.get(0);
    }
}
