package com.event.app.izhar.eventappbeta;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khanp on 12/3/2017.
 */

public class EventRequest extends StringRequest {

    private static final String EVENT_REQUEST_URL = "https://cq7243tk.000webhostapp.com/event_list.php";
    private Map<String, String> params;


    public EventRequest(String eventname, String date, Response.Listener<String> listener) {
        super(Method.POST, EVENT_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("eventname", eventname);
        params.put("date", date);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
