package com.event.app.izhar.eventapp;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by khanp on 11/2/2017.
 */

class EventListviewAdapter extends ArrayAdapter<String>{


    EventListviewAdapter(Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
