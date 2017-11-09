package com.event.app.izhar.eventapp;

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
import android.widget.ArrayAdapter;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_event, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CreateEvent();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });

        String[] eventItem = {"Wedding", "Event", "Event",
                "Event", "Event", "Event"};
        ListView listView = (ListView) view.findViewById(R.id.event_listview);
        ArrayAdapter<String> eventAdaptor = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, eventItem);
        listView.setAdapter(eventAdaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch(i){
                    case 0:
                        intent = new Intent(getContext(), EventDetailsNavigationDrawer.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getContext(), EventDetailsNavigationDrawer.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getContext(), EventDetailsNavigationDrawer.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getContext(), EventDetailsNavigationDrawer.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getContext(), EventDetailsNavigationDrawer.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(getContext(), EventDetailsNavigationDrawer.class);
                        startActivity(intent);
                        break;
                    default:
                    {
                        break;
                    }
                }
            }
        });
        return view;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

//public class EventFragment extends Fragment implements CreateEvent.OnFragmentInteractionListener {
//
//    ListView listView;
//    String HTTP_URL = "https://cq7243tk.000webhostapp.com/create_event.php";
//    String FinalJSonObject;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        listView = (ListView) listView.findViewById(R.id.event_listview);
//        super.onCreate(savedInstanceState);
//    }
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.activity_event, container, false);
//        listView = (ListView) view.findViewById(R.id.event_listview);
//
//        String[] eventItems = {"wedding", "Event", "Event",
//                "Event", "Event", "Event"};
//
//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>
//                (getContext(),android.R.layout.simple_list_item_1, eventItems);
//
//        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = new CreateEvent();
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();
//
//
//
//
//

//                StringRequest stringRequest = new StringRequest(HTTP_URL, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        FinalJSonObject = response;
//
//                        new ParseJSonDataClass(getContext()).execute();
//                    }
//                },
//                        new Response.ErrorListener() {
//
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//                requestQueue.add(stringRequest);
//            }
//        });
//
//        return view;
//    }



//    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {
//
//        public Context context;
//
//        // Creating List of Subject class.
//        List<EventContext> CustomEventNamesList;
//
//        public ParseJSonDataClass(Context context) {
//
//            this.context = context;
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//
//            try {
//
//                if (FinalJSonObject != null) {
//
//                    JSONArray jsonArray = null;
//                    try {
//                        jsonArray = new JSONArray(FinalJSonObject);
//                        JSONObject jsonObject;
//                        EventContext eventContext;
//                        CustomEventNamesList = new ArrayList<>();
//
//                        for (int i = 0; i < jsonArray.length(); i++) {
//
//                            eventContext = new EventContext();
//                            jsonObject = jsonArray.getJSONObject(i);
//
//                            eventContext.event_name = jsonObject.getString("event name");
//
//                            eventContext.event_date = jsonObject.getString("date");
//
//                            CustomEventNamesList.add(eventContext);
//                        }
//                    } catch (JSONException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result)
//
//        {
//            // After all done loading set complete CustomSubjectNamesList with application context to ListView adapter.
//            EventListviewAdapter adapter = new EventListviewAdapter(CustomEventNamesList, context);
//
//            // Setting up all data into ListView.
//            listView.setAdapter(adapter);
//
//        }
//    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }
//
//}


//
//
//
//
//
//        return view;
//    }
//

//    private class GetHttpResponse extends AsyncTask<void
//
//    void void>
//
//    {
//        private Context context;
//        String result;
//        List<EventContext> eventList;
//        public GetHttpResponse(Context context) {
//        this.context = context;
//    }
//    }


