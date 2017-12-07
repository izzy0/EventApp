package com.event.app.izhar.eventappbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.event.app.izhar.eventappbeta.DBConnection.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

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

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

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

                                Intent loginIntent = new Intent(LoginActivity.this, EventNavigationDrawer.class);
                                LoginActivity.this.startActivity(loginIntent);

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
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
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
