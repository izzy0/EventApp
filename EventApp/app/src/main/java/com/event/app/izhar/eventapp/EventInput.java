package com.event.app.izhar.eventapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by khanp on 11/13/2017.
 */

public class EventInput extends AsyncTask<Void, Integer, String> {
    Context c;
    String link;
    ListView eventList;

    public EventInput(Context c, String link, ListView eventList) {
        this.c = c;
        this.link = link;
        this.eventList = eventList;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String data = InputData();
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s != null) {
            EventParser e = new EventParser(c, s, eventList);
            e.execute();
        } else {
            Toast.makeText(c, "Error...Cannot retrieve data", Toast.LENGTH_SHORT).show();
        }
    }

    private String InputData() {
        InputStream stream = null;
        String line = null;
        try {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            stream = new BufferedInputStream(con.getInputStream());

            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(stream));

            StringBuffer stringBuffer = new StringBuffer();

            if (stringBuffer != null) {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + "/n");
                }
            } else {
                return null;
            }

            return stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
