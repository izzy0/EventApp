package com.event.app.izhar.eventappbeta.Service.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class CreateEventRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://cq7243tk.000webhostapp.com/create_event.php";
    private Map<String, String> params;

    public CreateEventRequest(int user, String eventDate, String eventName, String location,
                              Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("user", String.valueOf(user));
        params.put("date", eventDate);
        params.put("location", location);
        params.put("eventname", eventName);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

