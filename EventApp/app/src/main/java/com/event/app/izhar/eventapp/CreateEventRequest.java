package com.event.app.izhar.eventapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Izhar on 10/9/2017.
 */

public class CreateEventRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://cq7243tk.000webhostapp.com/create_event.php";
    private Map<String, String> params;

    public CreateEventRequest(String eventDate, String eventHost, String location, String eventName,
                              Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("date", eventDate);
        params.put("eventHost", eventHost);
        params.put("location", location);
        params.put("eventname", eventName);
//        params.put("images", images);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}

