package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DeleteAdminMembersActivity extends AppCompatActivity {

    public Button delete;

    public void init() {
        delete = (Button) findViewById(R.id.user_delete_id_button);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View d) {

                Intent deleteUser = new Intent(DeleteAdminMembersActivity.this, DeleteAdminMembersActivity.class);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_admin_members_activity);
        init();
    }
}
