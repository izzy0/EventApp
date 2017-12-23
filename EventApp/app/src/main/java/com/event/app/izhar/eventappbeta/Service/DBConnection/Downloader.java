package com.event.app.izhar.eventappbeta.Service.DBConnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void,Void,String> {

    Context context;
    String urlAddress;
    GridView imageGridView;

    ProgressDialog progressDialog;

    public Downloader(Context context, String urlAddress, GridView imageGridView) {
        this.context = context;
        this.urlAddress = urlAddress;
        this.imageGridView = imageGridView;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Retrieve");
        progressDialog.setMessage("Retrieving ... Please wait");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        progressDialog.dismiss();

        if (jsonData == null){
            Toast.makeText(context, "Images where unable to load", Toast.LENGTH_SHORT).show();
        } else{
            //parse
            DataParser dataParser = new DataParser(context,jsonData, imageGridView);
            dataParser.execute();
        }
    }

    private String downloadData(){
        HttpURLConnection con = Connector.connect(urlAddress);

        if (con == null ){
            return null;
        }

        try{
            InputStream inputStream = new BufferedInputStream(con.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line = bufferedReader.readLine())!=null){
                    jsonData.append(line+"\n");
            }

            bufferedReader.close();
            inputStream.close();

            return jsonData.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
