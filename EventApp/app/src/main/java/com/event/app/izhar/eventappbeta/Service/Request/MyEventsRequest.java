package com.event.app.izhar.eventappbeta.Service.Request;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class MyEventsRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://cq7243tk.000webhostapp.com/my_event_list.php";
    private Map<String, String> params;

    public MyEventsRequest(int userid, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("user", String.valueOf(userid));

        Log.i("The Request Of MyEvents", params.toString());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}