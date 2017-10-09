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
    public Button menu;

    public void init(){
        add = (Button)findViewById(R.id.create_event_id_button);
        search = (Button)findViewById(R.id.search_id_button);
        menu = (Button) findViewById(R.id.side_menu_id_button);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {

                Intent menu = new Intent(EventActivity.this, EventActivity.class);
                startActivity(menu);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                 Intent addEvent = new Intent(EventActivity.this, EventDetailsActivity.class);
                startActivity(addEvent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {

                Intent search = new Intent(EventActivity.this, EventActivity.class);
                startActivity(search);

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


