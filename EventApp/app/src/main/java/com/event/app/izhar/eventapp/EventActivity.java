package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by khanp on 10/5/2017.
 */

public class EventActivity extends AppCompatActivity{

    public Button add;
    public Button search;

    public void init(){
        add = (Button)findViewById(R.id.create_event_id_buttonbutton);
        search = (Button)findViewById(R.id.search_id_buttonbutton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {

                 Intent event = new Intent(EventActivity.this, EventDetailsActivity.class);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {

                Intent search = new Intent(EventActivity.this, EventActivity.class);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_activity);
        init();
    }
}


