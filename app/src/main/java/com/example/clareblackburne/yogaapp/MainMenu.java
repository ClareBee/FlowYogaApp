package com.example.clareblackburne.yogaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by clareblackburne on 11/11/2017.
 */

public class MainMenu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.add_pose){
            Intent intent = new Intent(this, AddPoseActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId() == R.id.add_session){
            Intent intent = new Intent(this, AddSessionActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId() == R.id.all_poses){
            Intent intent = new Intent(this, TopPosesActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId() == R.id.all_sessions){
            Intent intent = new Intent(this, TopSessionsActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId() == R.id.quotationMenu){
            Intent intent = new Intent(this, QuotationActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
