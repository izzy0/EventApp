package com.event.app.izhar.eventappbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginTwoActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_two);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            loginAction();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAction();
            }
        });
    }

    private void loginAction(){
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        if (username != null || password != null ){
            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success) {
                            //TODO FIX PARSE USER
                            //new ParseUser(jsonResponse.toString());
                            createUser(jsonResponse);

                            Intent loginIntent = new Intent(LoginTwoActivity.this, EventNavigationDrawer.class);
                            LoginTwoActivity.this.startActivity(loginIntent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Login Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
            System.out.println("parameters "+ loginRequest.getParams());
            RequestQueue queue = Volley.newRequestQueue(LoginTwoActivity.this);
            queue.add(loginRequest);

        }else if(username == null){
            Toast.makeText(LoginTwoActivity.this, "Enter username", Toast.LENGTH_SHORT).show();

        }else if (password == null){
            Toast.makeText(LoginTwoActivity.this, "Enter password", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(LoginTwoActivity.this, "Please fill login fields", Toast.LENGTH_SHORT).show();
        }
    }
    private void registerAction(){
        LoginTwoActivity.this.startActivity(new Intent(LoginTwoActivity.this, RegisterUserActivity.class));
    }

    private void createUser(JSONObject jsonResponse){
        try {
            String username = jsonResponse.getString("username");
            String password = jsonResponse.getString("password");
            String firstName = jsonResponse.getString("first_name");
            String lastName = jsonResponse.getString("last_name");
            String email = jsonResponse.getString("email");
            int id = Integer.parseInt(jsonResponse.getString("user_id"));

            new User(id, username,password,firstName,lastName,email);

            Toast.makeText(this, "Welcome "+ User.getUsername(), Toast.LENGTH_SHORT).show();

            //ParseUser parseUser = new ParseUser(jsonResponse.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
