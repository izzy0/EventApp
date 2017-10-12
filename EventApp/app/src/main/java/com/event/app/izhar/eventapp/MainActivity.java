package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button add;
    public Button search;
    public Button menu;

    public void init() {
        add = (Button) findViewById(R.id.create_event_id_button);
        search = (Button) findViewById(R.id.search_id_button);
        menu = (Button) findViewById(R.id.side_menu_id_button);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {

                Intent menu = new Intent(MainActivity.this, MainActivity.class);
                startActivity(menu);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View a) {

                Intent addEvent = new Intent(MainActivity.this, EventDetailsActivity.class);
                startActivity(addEvent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                Intent search = new Intent(MainActivity.this, MainActivity.class);
                startActivity(search);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
