package com.event.app.izhar.eventappbeta.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.event.app.izhar.eventappbeta.R;
import com.event.app.izhar.eventappbeta.Service.Request.RegisterRequest;
import com.event.app.izhar.eventappbeta.User;
import com.google.gson.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {

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
        final String uname = username.getText().toString();
        final String passw = password.getText().toString();
        final String fname = firstName.getText().toString();
        final String lname = lastName.getText().toString();
        final String emailString = email.getText().toString();


//        JsonGsonParser parser = new JsonGsonParser();
//        parser.SerializeFile(user);
//        Log.i("Parser", parser.toString());

        if(validateFields(uname, passw, fname, lname, emailString)){
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.i("tagconvertstr", "["+response+"]");

                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");

                        int id = jsonObject.getInt("user_id");
                        if (success){
                            new User(id, uname, passw, fname, lname, emailString);
                        }
                        System.out.println("boolean json"+success);

                        processFinish(success);

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
    }

    private boolean validateFields(String uname, String pass, String fname, String lname, String email ){
        if(!(uname.isEmpty() || pass.isEmpty() || fname.isEmpty() || lname.isEmpty() || email.isEmpty()) ){
            if(pass.length() < 6){
                Toast.makeText(this, "Password has to be over 6 character", Toast.LENGTH_SHORT).show();
            }
            if(!isEmailValid(email)){
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show();
            }
            if(isEmailValid(email) && pass.length() > 6){
                return true;
            }
            return false;
        }else{
            Toast.makeText(this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void processFinish(boolean result) {
        if (result) {
            Toast.makeText(this, "Register Successful!",
                    Toast.LENGTH_LONG).show();
            Intent next = new Intent(this, EventNavigationDrawer.class);
            startActivity(next);
        }else{
            Toast.makeText(getApplicationContext(), "Username or email already taken ;(",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
