package com.event.app.izhar.eventapp;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class EventRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://cq7243tk.000webhostapp.com/event_list.php";
    private Map<String, String> params;

    public EventRequest(Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);

        params = new HashMap<>();

        Log.i("The Request Of Event", params.toString());
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}