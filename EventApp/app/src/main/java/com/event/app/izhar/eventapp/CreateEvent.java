package com.event.app.izhar.eventapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.Date;

@SuppressLint("ValidFragment")
public class CreateEvent extends Fragment {

    private OnFragmentInteractionListener mListener;

    private String eventHost;

    private EditText eventName;
    private EditText eventType;
    //todo change date type
    private EditText eventDate;

    private Time startTime;
    private Time endTime;

    private Button createEventBtn;

    @SuppressLint("ValidFragment")
    public CreateEvent() {
    }

    // TODO: Rename and change types and number of parameters
    public static CreateEvent newInstance() {
        CreateEvent fragment = new CreateEvent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);

        eventName = (EditText) view.findViewById(R.id.createevent_eventName);
        eventType = (EditText) view.findViewById(R.id.createevent_eventType);
        eventDate = (EditText) view.findViewById(R.id.createevent_eventTime);
        createEventBtn = (Button) view.findViewById(R.id.add_event_button);

        Button addButton = (Button) view.findViewById(R.id.add_event_button);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String eventNameStr = eventName.getText().toString();
//        final String eventTypeStr = eventType.getText().toString();
                final String eventDateStr = eventDate.getText().toString();

                //todo pass in user info
                Event event = new Event(eventDateStr, "admmin", eventNameStr);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Fragment fragment = null;

                        try {
                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            System.out.println("boolean json" + success);

                            // todo call proccessFinished here
                            if (success) {

                                Log.i("Success", "[IN CREATE EVENT");
                                Toast.makeText(getContext(), "Event Created", Toast.LENGTH_SHORT).show();

                                fragment = new EventFragment();
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, fragment);
                                fragmentTransaction.commit();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                String passw = "loc", host = "admin";
                CreateEventRequest request = new CreateEventRequest(eventDateStr, host, passw, eventNameStr, responseListener);
                Log.i("THE CREATE EVENT LOG", "[" + request.getParams() + "]");
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(request);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}