package com.event.app.izhar.eventapp;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Izhar on 10/9/2017.
 */

public class AppImageRequest extends StringRequest {

    private static final String UPLOAD_REQUEST_URL = "https://cq7243tk.000webhostapp.com/upload.php";
    private Map<String, String> params;

    public AppImageRequest(String encodedImage, Response.Listener<String> listener) {
        super(Method.POST, UPLOAD_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("image", encodedImage);
        Log.d("IMAGE ENCODE", encodedImage);


    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}

