package com.event.app.izhar.eventappbeta;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class EventFragment extends Fragment implements CreateEvent.OnFragmentInteractionListener {
    private static final String HTTP_URL = "http://cq7243tk.000webhostapp.com/event_list.php";
    private ListView listView;
    private String finalJSOnObject;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_event, container, false);

        listView = (ListView) view.findViewById(R.id.event_listview);

        queryDatabaseIntoJsonResponse();

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.event_refresh);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "List Synced", Toast.LENGTH_SHORT).show();
                queryDatabaseIntoJsonResponse();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), EventDetailsNavigationDrawer.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void queryDatabaseIntoJsonResponse() {
        // Creating StringRequest and set the JSON server URL in here.
        StringRequest stringRequest = new StringRequest(HTTP_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // After done Loading store JSON response in FinalJSonObject string variable.
                        finalJSOnObject = response;

                        // Calling method to parse JSON object.
                        new ParseJSonDataClass(getActivity()).execute();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // Showing error message if something goes wrong.
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        // Creating String Request Object.
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        // Passing String request into RequestQueue.
        requestQueue.add(stringRequest);
    }

    // Creating method to parse JSON object.
    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {

        public Context context;

        // Creating List of Subject class.
        List<EventContext> eventContextList;

        public ParseJSonDataClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {

                // Checking whether FinalJSonObject is not equals to null.
                if (finalJSOnObject != null) {

                    // Creating and setting up JSON array as null.
                    JSONArray jsonArray = null;
                    try {
                        // Adding JSON response object into JSON array.
                        jsonArray = new JSONArray(finalJSOnObject);

                        // Creating JSON Object.
                        JSONObject jsonObject;

                        // Creating Subject class object.
                        EventContext eventContext;

                        // Defining eventContextList AS Array List.
                        eventContextList = new ArrayList<EventContext>();

                        for (int i = 0; i < jsonArray.length(); i++) {

                            eventContext = new EventContext();

                            jsonObject = jsonArray.getJSONObject(i);

                            //Storing ID into subject list.
                            eventContext.event_name = jsonObject.getString("eventname");

                            //Storing Subject name in subject list.
                            eventContext.date = jsonObject.getString("date");

                            // Adding subject list object into eventContextList.
                            eventContextList.add(eventContext);
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // After all done loading set complete eventContextList with application context to ListView adapter.
            EventListviewAdapter adapter = new EventListviewAdapter(eventContextList, getActivity());

            // Setting up all data into ListView.
            listView.setAdapter(adapter);
        }
    }

}

