package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.*;

import java.util.HashMap;

public class LoginTwoActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener {

    private EditText username, password;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_two);

        username = (EditText) findViewById(R.id.username_id);
        password = (EditText) findViewById(R.id.password_id);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        Button btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterUserActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {

        HashMap postData = new HashMap();
        postData.put("btnLogin", "Login");
        postData.put("mobile", "android");
        postData.put("txtUsername", username.getText().toString());
        postData.put("txtPassword", password.getText().toString());

        PostResponseAsyncTask loginTask = new PostResponseAsyncTask(this, postData,this);
        loginTask.execute("http://cq7243tk.000webhostapp.com/login.php");
    }


    @Override
    public void processFinish(String result) {

        if (result.equals("success")) {
            Toast.makeText(this, "Login Successfully!",
                    Toast.LENGTH_LONG).show();
            Intent next = new Intent(this, NavigationDrawer.class);
            startActivity(next);
        }
        else{
            Toast.makeText(this, "Login Failed!", Toast.LENGTH_LONG).show();
        }
    }
}
