package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by khanp on 10/5/2017.
 */

public class GalleryDetailsActivity extends AppCompatActivity {

    public Button menu;
    public Button flag;
    public Button like;
    public Button previous;
    public Button next;

    public void init() {
        menu = (Button) findViewById(R.id.side_menu_id_button);
        flag = (Button) findViewById(R.id.flag_image_id_button);
        like = (Button) findViewById(R.id.like_id_button);
        next = (Button) findViewById(R.id.right_arrow_id_button);
        previous = (Button) findViewById(R.id.left_arrow_id_button);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {

                Intent menu = new Intent(GalleryDetailsActivity.this, GalleryActivity.class);
            }
        });

        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View f) {

                Intent flagPhoto = new Intent(GalleryDetailsActivity.this, GalleryActivity.class);
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View l) {

                Intent likePhoto = new Intent(GalleryDetailsActivity.this, GalleryActivity.class);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pr) {

                Intent previousPhoto = new Intent(GalleryDetailsActivity.this, GalleryActivity.class);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View f) {

                Intent nextPhoto = new Intent(GalleryDetailsActivity.this, GalleryActivity.class);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_details_activity);
        init();
    }
}
