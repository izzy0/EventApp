package com.event.app.izhar.eventapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

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

        String[] eventItem = {"wedding1", "wedding2", "wedding3",
                "birthday", "halloween party", "2017 New years Eve Party"};
        ListView listView = (ListView) view.findViewById(R.id.event_listview);
        ArrayAdapter<String> eventAdaptor = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, eventItem);
//        eventAdaptor = new EventListviewAdapter();

        listView.setAdapter(eventAdaptor);


        
        return view;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        
    }
}
