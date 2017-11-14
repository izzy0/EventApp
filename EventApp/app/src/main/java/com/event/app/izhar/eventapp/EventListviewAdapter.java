package com.event.app.izhar.eventapp;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanp on 11/2/2017.
 */

//public class EventListviewAdapter extends BaseAdapter {
//
//    Context context;
//
//    List<EventContext> TempEventList;
//
//    public EventListviewAdapter(List<EventContext> listValue, Context context) {
//        this.context = context;
//        this.TempEventList = listValue;
//    }
//
//    @Override
//    public int getCount() {
//        return this.TempEventList.size();
//    }
//
//    @Override
//    public Object getItem(int postion) {
//        return this.TempEventList.get(postion);
//    }
//
//    @Override
//    public long getItemId(int postion) {
//        return postion;
//    }
//
//    @Override
//    public View getView(int postion, View customView, ViewGroup parent) {
//        {
//            ViewEvent viewEvent = null;
//
//            if (customView == null) {
//                viewEvent = new ViewEvent();
//                LayoutInflater layoutInflater = (LayoutInflater)
//                        this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//                customView = layoutInflater.inflate(R.layout.activity_event, null);
//                viewEvent.tvEventName = (TextView) customView.findViewById(R.id.tv_event_name_id);
//                viewEvent.tvEventDate = (TextView) customView.findViewById(R.id.tv_date_id);
//
//                customView.setTag(viewEvent);
//            } else {
//                viewEvent = (ViewEvent) customView.getTag();
//            }
//            viewEvent.tvEventName.setText(TempEventList.get(postion).event_name);
//            viewEvent.tvEventDate.setText(TempEventList.get(postion).event_date);
//
//            return customView;
//        }
//    }
//}
//
//class ViewEvent {
//    TextView tvEventName;
//    TextView tvEventDate;
//}


class EventListviewAdapter extends ArrayAdapter<String> {


    EventListviewAdapter(Context context, String[] events) {
        super(context, R.layout.event_listview_adapter, events);
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater eventInflater = LayoutInflater.from(getContext());
        View eventListView = eventInflater.inflate(R.layout.event_listview_adapter, parent, false);

        String singleEventItem = getItem(position);
//        String secondEventItem = getItem(position);
        TextView eventName = (TextView) eventListView.findViewById(R.id.tv_event_name_id);
//        TextView eventDate = (TextView) eventListView.findViewById(R.id.tv_date_id);

        eventName.setText(singleEventItem);
//        eventDate.setText(secondEventItem);
        return eventListView;
    }
}
