package com.event.app.izhar.eventappbeta.Service.DBConnection;

import android.os.AsyncTask;

import com.event.app.izhar.eventappbeta.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Izhar on 12/5/2017.
 */

public class ParseUser extends AsyncTask<Void, Void, Integer> {

    String jsonDate;

    public ParseUser(String jsonDate) {
        this.jsonDate = jsonDate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parseData();
    }

    private int parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonDate);
            JSONObject jsonObject = null;

            User user;
            //TODO the jsonObject getters string params will need to be changed to the db column name
            for (int i = 0; i<jsonArray.length(); i++){

                jsonObject = jsonArray.getJSONObject(i);

//                int id = jsonObject.getInt("__pkuserid");
                String username = jsonObject.getString("username");
                String password = jsonObject.getString("password");
                String firstName = jsonObject.getString("first_name");
                String lastName = jsonObject.getString("last_name");
                String email = jsonObject.getString("email");
                int id = Integer.parseInt(jsonObject.getString("user_id"));

                new User(id,username,password,firstName,lastName,email);
            }
            //if successful
            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //if failed
        return 0;
    }
}
