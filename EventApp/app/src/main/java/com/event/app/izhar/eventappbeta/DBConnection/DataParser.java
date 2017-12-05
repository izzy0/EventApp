package com.event.app.izhar.eventappbeta.DBConnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import com.event.app.izhar.eventappbeta.Adapter.GridViewAdapter;
import com.event.app.izhar.eventappbeta.ImageObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Izhar on 12/4/2017.
 */

public class DataParser extends AsyncTask<Void, Void, Integer> {

    Context context;
    String jsonDate;
    GridView imageGridView;

    ProgressDialog progressDialog;
    ArrayList<ImageObject> imageObjectArrayList = new ArrayList<>();

    public DataParser(Context context, String jsonDate, GridView imageGridView) {
        this.context = context;
        this.jsonDate = jsonDate;
        this.imageGridView = imageGridView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("parse");
        progressDialog.setMessage("Parsing ... Please wait");
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        progressDialog.dismiss();

        if (result==0){
            Toast.makeText(context, "Unable to parse", Toast.LENGTH_SHORT).show();
        }else{
            //Bind data to grid view
            GridViewAdapter gridViewAdapter = new GridViewAdapter(context, imageObjectArrayList);
            imageGridView.setAdapter(gridViewAdapter);
        }
    }

    private int parseData(){
        try{
            JSONArray jsonArray = new JSONArray(jsonDate);
            JSONObject jsonObject = null;

            imageObjectArrayList.clear();
            ImageObject imageObject;
            //TODO the jsonObject getters string params will need to be changed to the db column name
            for (int i = 0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
//                int id = jsonObject.getInt("__pkphotoid");
//                String url = jsonObject.getString("photo_path");
                int id = jsonObject.getInt("id");
                String url = jsonObject.getString("url");

                imageObject = new ImageObject();
                imageObject.setId(id);
                imageObject.setImageURL(url);

                imageObjectArrayList.add(imageObject);
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
