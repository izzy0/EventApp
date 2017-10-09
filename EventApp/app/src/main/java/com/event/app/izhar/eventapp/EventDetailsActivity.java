package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by khanp on 10/5/2017.
 */

public class EventDetailsActivity extends AppCompatActivity {

    public Button menu;
    public Button search;
    public Button popular;
    public Button host;
    public Button shared;
    public Button upload;

    public void init() {
        menu = (Button) findViewById(R.id.side_menu_id_button);
        search = (Button) findViewById(R.id.search_id_button);
        popular = (Button) findViewById(R.id.popular_photos_id_button);
        shared = (Button) findViewById(R.id.shared_photo_id_button);
        host = (Button) findViewById(R.id.host_photo_id_button);
        upload = (Button) findViewById(R.id.upload_id_button);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {

                Intent menu = new Intent(EventDetailsActivity.this, EventDetailsActivity.class);
                startActivity(menu);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {

                Intent search = new Intent(EventDetailsActivity.this, EventActivity.class);
                startActivity(search);

            }
        });

        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pp) {

                Intent popularPhotos = new Intent(EventDetailsActivity.this, EventDetailsActivity.class);
                startActivity(popularPhotos);
            }

        });

        shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View sp) {

                Intent sharedPhotos = new Intent(EventDetailsActivity.this, EventDetailsActivity.class);
                startActivity(sharedPhotos);
            }
        });

        host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View hp) {

                Intent hostPhotos = new Intent(EventDetailsActivity.this, EventDetailsActivity.class);
                startActivity(hostPhotos);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View u) {

                Intent uploadPhotos = new Intent(EventDetailsActivity.this, EventDetailsActivity.class);
                startActivity(uploadPhotos);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details_activity);
        init();
    }
}
