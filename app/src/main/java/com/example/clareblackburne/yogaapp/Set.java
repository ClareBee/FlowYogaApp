package com.example.clareblackburne.yogaapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_CHAKRA;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_DURATION;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_ID;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_IMAGE;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_NAME;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_COLUMN_SANSKRITNAME;
import static com.example.clareblackburne.yogaapp.DBHelper.POSES_TABLE_NAME;
import static com.example.clareblackburne.yogaapp.DBHelper.SET_COLUMN_ID;
import static com.example.clareblackburne.yogaapp.DBHelper.SET_POSES_ID;
import static com.example.clareblackburne.yogaapp.DBHelper.SET_SESSION_ID;
import static com.example.clareblackburne.yogaapp.DBHelper.SET_TABLE_NAME;

/**
 * Created by clareblackburne on 13/11/2017.
 */

public class Set {

    private int id;
    private int session_id;
    private int poses_id;

    public Set(int id, int session_id, int poses_id){
        this.id = id;
        this.session_id = session_id;
        this.poses_id = poses_id;
    }

    public Set(int session_id, int poses_id) {
        this.session_id = session_id;
        this.poses_id = poses_id;
    }

    public int getId() {
        return id;
    }

    public int getSession_id() {
        return session_id;
    }

    public int getPoses_id() {
        return poses_id;
    }


    public static ArrayList<Set> all(DBHelper dbHelper){
        ArrayList<Set> sets = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SET_TABLE_NAME, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(SET_COLUMN_ID));
            int session_id = cursor.getInt(cursor.getColumnIndex(SET_SESSION_ID));
            int poses_id = cursor.getInt(cursor.getColumnIndex(SET_POSES_ID));
            Set set = new Set(id, session_id, poses_id);
            sets.add(set);
        }
        cursor.close();
        return sets;
    }

    public ArrayList<Pose> allPosesInSet(Integer session_id, DBHelper dbHelper) {
        ArrayList<Pose> allPosesInThisSet = new ArrayList<>();
        String selectAllQuery = "SELECT poses.* FROM " + POSES_TABLE_NAME + " INNER JOIN " + SET_TABLE_NAME + " ON poses.id = sessions_poses.poses_id WHERE sessions_poses.session_id = ? ";
        String[] values = {session_id.toString()};
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, values);
        if (cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex(POSES_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(POSES_COLUMN_NAME));
                String sanskritName = cursor.getString(cursor.getColumnIndex(POSES_COLUMN_SANSKRITNAME));
                String chakra = cursor.getString(cursor.getColumnIndex(POSES_COLUMN_CHAKRA));
                int duration = cursor.getInt(cursor.getColumnIndex(POSES_COLUMN_DURATION));
                int image = cursor.getInt(cursor.getColumnIndex(POSES_COLUMN_IMAGE));
                Pose pose = new Pose(name, sanskritName, chakra, duration, image);
                allPosesInThisSet.add(pose);
            }
            cursor.close();
        } else {
            allPosesInThisSet.add(null);
        }

        return allPosesInThisSet;
    }

    public boolean update(Integer session_id, Integer poses_id, DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SET_SESSION_ID, session_id);
        contentValues.put(SET_POSES_ID, poses_id);
        String selection = "session_id=?";
        Integer id = this.getId();
        String[] values = new String[] {String.valueOf(id)};
        db.update(SET_TABLE_NAME, contentValues, selection, values);
        return true;
    }

    public boolean save(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SET_SESSION_ID, this.session_id);
        cv.put(SET_POSES_ID, this.poses_id);
        db.insert(SET_TABLE_NAME, null, cv);
        return true;
    }

    public boolean deleteAllSet(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + SET_TABLE_NAME);
        return true;
    }

}
