package com.event.app.izhar.eventapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by khanp on 11/13/2017.
 */

public class EventParser extends AsyncTask<Void, Integer, Integer> {
    Context c;
    String data;
    ListView eventList;
    ArrayList<String> events = new ArrayList<>();

    public EventParser(Context c, String data, ListView eventList) {
        this.c = c;
        this.data = data;
        this.eventList = eventList;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.EventParse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if (integer == 1) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, events);
            eventList.setAdapter(adapter);

            //TODO
//            eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    if(view != null){
//                        for(i = 0; ){
//
//                        }
//                    }
//
//                }
//            });

        } else {
            Toast.makeText(c, "Error..Cannot parse", Toast.LENGTH_SHORT).show();
        }
    }

    private int EventParse() {
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = null;

            events.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("eventname");
                String date = jsonObject.getString("date");

                events.add(name);
            }
            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
