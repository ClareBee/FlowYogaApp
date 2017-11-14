package com.example.clareblackburne.yogaapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by clareblackburne on 11/11/2017.
 */

public class TopSessionsAdapter extends ArrayAdapter<Session> {


    public TopSessionsAdapter(Context context, ArrayList<Session> sessions){ super(context, 0, sessions);}


    public View getView(int position, View listItemView, ViewGroup parent){

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.session_item, parent, false );
        }
        final Session currentSession = getItem(position);
        TextView nameSession = (TextView)listItemView.findViewById(R.id.sessionList);
        nameSession.setText(currentSession.getName().toString());
        TextView day = (TextView)listItemView.findViewById(R.id.sessionDay);
        day.setText(currentSession.getDay().toString());

        ImageButton imageButton = (ImageButton) listItemView.findViewById(R.id.completeButtonList);
        final View finalListItemView = listItemView;
        final ViewGroup parent2 = parent;
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(finalListItemView.getContext());
                currentSession.updateAsComplete(dbHelper);
                currentSession.setStatus("Y");
                notifyDataSetChanged();
            }

        });
        if(currentSession.getStatus().equalsIgnoreCase("Y")){
            listItemView.setBackgroundColor(Color.rgb(222, 217, 226));
        }
        listItemView.setTag(currentSession);
        return listItemView;
    }

}




