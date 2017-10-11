package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button login;
    public Button signUp;

    public void init() {
        login = (Button) findViewById(R.id.login_id_button);
        signUp = (Button) findViewById(R.id.sign_up_id_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {

                Intent authenticate = new Intent(MainActivity.this, EventActivity.class);
                startActivity(authenticate);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {

                Intent register = new Intent(MainActivity.this, EventActivity.class);
                startActivity(register);

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
