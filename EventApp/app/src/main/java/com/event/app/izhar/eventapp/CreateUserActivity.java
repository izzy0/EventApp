package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CreateUserActivity extends AppCompatActivity {

    public Button newUser;

    public void init() {
        newUser = (Button) findViewById(R.id.create_user_id_button);

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {

                Intent createUser = new Intent(CreateUserActivity.this, CreateUserActivity.class);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_activity);
    }
}
