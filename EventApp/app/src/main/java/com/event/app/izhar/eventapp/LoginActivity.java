package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.*;

import java.util.HashMap;
import java.util.Map;


/**
 * A login screen that offers login via user/password.
 */
public class LoginActivity extends AppCompatActivity {

    String URL = "http://cq7243tk.000webhostapp.com/index.php";
    //JSONParser jsonParser = new JSONParser();
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        Button mLoginButton = (Button) findViewById(R.id.btnLogin);
        Button mRegisterButton = (Button) findViewById(R.id.btnRegister);

        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new AttemptLogin();
            }
        });

        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //newRegister();
            }
        });
    }

    private class AttemptLogin
    {
        final EditText mUsername = (EditText) findViewById(R.id.etUsername);
        final EditText mPassword = (EditText) findViewById(R.id.etPassword);

        String Username = mUsername.getText().toString();
        String Password = mPassword.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);

                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), EventFragment.class));
                        } else
                        {
                            Toast.makeText(getApplicationContext(),obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener(){
                public void onErrorResponse(VolleyError error){
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                protected Map<String,String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("username", Username);
                    params.put("password", Password);
                    return params;
                }
            };
    }

            //String password = args[1];
            //String name = args [0];
            //ArrayList params = new ArrayList();
            //params.add(new BasicNameValuePair("username", name));
            //params.add(new BasicNameValuePair("password", password));
            //JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);
            //return json;

    public void newRegister(){
        Toast.makeText(getApplicationContext(),"I Don't Do Shit.", Toast.LENGTH_SHORT).show();
    }
}

