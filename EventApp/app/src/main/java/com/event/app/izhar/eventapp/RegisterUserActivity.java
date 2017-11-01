package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.*;
import com.kosalgeek.asynctask.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class RegisterUserActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener {

    //    private EditText username, password, email, firstName, lastName;
    private Button newUser;
    private EditText username;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private EditText email;


    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

         username = (EditText) findViewById(R.id.create_username_id_textfield);
         password = (EditText) findViewById(R.id.create_user_password_id);
         firstName = (EditText) findViewById(R.id.first_name_id);
         lastName = (EditText) findViewById(R.id.last_name_id);
         email = (EditText) findViewById(R.id.create_email_id_textfield);

        newUser = (Button) findViewById(R.id.create_user_id_button);
        newUser.setOnClickListener(this);
    }

    public void onClick(View v) {
        final String fname = firstName.getText().toString();
        final String lname = lastName.getText().toString();
        final String uname = username.getText().toString();
        final String passw = password.getText().toString();
        final String emailString = email.getText().toString();

        User user = new User(uname, passw, fname, lname, emailString);
        JsonGsonParser parser = new JsonGsonParser();
        parser.SerializeFile(user);
        Log.i("Parser", parser.toString());

//        String jsonObjectString = gson.toJson(user);

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.i("tagconvertstr", "["+response+"]");
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        System.out.println("boolean json"+success);

                        if(success){
                            Intent intent = new Intent(RegisterUserActivity.this, EventNavigationDrawer.class);
//                            intent.putExtra("username", uname);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(), "Username or email already taken ;(",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            RegisterRequest request = new RegisterRequest(fname, lname, uname, passw, emailString, responseListener);
            System.out.println("parameters "+request.getParams());
            Log.i("THE Register LOG","["+ request.getParams()+"]");
            RequestQueue queue = Volley.newRequestQueue(RegisterUserActivity.this);
            queue.add(request);
    }

    @Override
    public void processFinish(String result) {
        if (result.equals("success")) {
            Toast.makeText(this, "Register Successful!",
                    Toast.LENGTH_LONG).show();
            Intent next = new Intent(this, EventNavigationDrawer.class);
            startActivity(next);
        }
    }
}
