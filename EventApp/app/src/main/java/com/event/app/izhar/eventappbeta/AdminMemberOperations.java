package com.event.app.izhar.eventappbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminMemberOperations extends AppCompatActivity {

    public Button delete;
//TODO implement add admin members and delete admin members
    public void init() {
        delete = (Button) findViewById(R.id.user_delete_id_button);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View d) {

                Intent deleteUser = new Intent(AdminMemberOperations.this, AdminMemberOperations.class);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_delete_admin_members);
        init();
    }
}
