package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CreateEventActivity extends AppCompatActivity {

    public Button createEvent;

    public void init() {
        createEvent = (Button) findViewById(R.id.create_event_id_button);

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {

                Intent event = new Intent(CreateEventActivity.this, CreateEventActivity.class);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_activity);
    }
}
