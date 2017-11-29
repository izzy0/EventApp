package com.event.app.izhar.eventapp;

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
                            String username = jsonResponse.getString("username");
                            Intent loginIntent = new Intent(LoginTwoActivity.this, EventNavigationDrawer.class);
                            loginIntent.putExtra("username", username);
                            LoginTwoActivity.this.startActivity(loginIntent);

                            Toast.makeText(getApplicationContext(), "Success",
                                    Toast.LENGTH_LONG).show();
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
}
