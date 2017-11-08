package com.event.app.izhar.eventapp;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by khanp on 11/2/2017.
 */

class EventListviewAdapter extends ArrayAdapter<String>{


    EventListviewAdapter(Context context, String[] eventItem) {
        super(context, R.layout.event_listview_adapter, eventItem);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater eventInflater = LayoutInflater.from(getContext());
        View eventListView = eventInflater.inflate(R.layout.event_listview_adapter, parent, false);

        String singleEventItem = getItem(position);
        TextView eventName = (TextView) eventListView.findViewById(R.id.tv_event_name_id);
        TextView eventDate = (TextView) eventListView.findViewById(R.id.tv_date_id);

        eventName.setText("");
        eventDate.setText("");

        return eventListView;
    }
}
