package com.event.app.izhar.eventappbeta;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://cq7243tk.000webhostapp.com/logins.php";
    private Map<String, String> params;

    public LoginRequest( String username, String password,
                         Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        Log.i("The Request Of Login", params.toString());
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}