package com.event.app.izhar.eventappbeta.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.event.app.izhar.eventappbeta.EventContext;
import com.event.app.izhar.eventappbeta.R;

import java.util.List;

/**
 * Created by khanp on 11/2/2017.
 */

public class EventListviewAdapter extends BaseAdapter {

    Context context;

    List<EventContext> TempEventList;

    public EventListviewAdapter(List<EventContext> listValue, Context context) {
        this.context = context;
        this.TempEventList = listValue;
    }

    @Override
    public int getCount() {
        return this.TempEventList.size();
    }

    @Override
    public Object getItem(int postion) {
        return this.TempEventList.get(postion);
    }

    @Override
    public long getItemId(int postion) {
        return postion;
    }

    @Override
    public View getView(int postion, View customView, ViewGroup parent) {
        {
            ViewEvent viewEvent = null;

            if (customView == null) {
                viewEvent = new ViewEvent();
                LayoutInflater layoutInflater = (LayoutInflater)
                        this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                customView = layoutInflater.inflate(R.layout.event_listview_adapter, null);
                viewEvent.tvEventName = (TextView) customView.findViewById(R.id.tv_event_name_id);
                viewEvent.tvEventDate = (TextView) customView.findViewById(R.id.tv_date_id);

                customView.setTag(viewEvent);
            } else {
                viewEvent = (ViewEvent) customView.getTag();
            }
            viewEvent.tvEventName.setText(TempEventList.get(postion).event_name);
            viewEvent.tvEventDate.setText(TempEventList.get(postion).date);

            return customView;
        }
    }
}

class ViewEvent {
    TextView tvEventName;
    TextView tvEventDate;
}


//class EventListviewAdapter extends ArrayAdapter<String> {
//
//
//    EventListviewAdapter(Context context, String[] events) {
//        super(context, R.layout.event_listview_adapter, events);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater eventInflater = LayoutInflater.from(getContext());
//        View eventListView = eventInflater.inflate(R.layout.event_listview_adapter, parent, false);
//
//        String singleEventItem = getItem(position);
////        String secondEventItem = getItem(position);
//        TextView eventName = (TextView) eventListView.findViewById(R.id.tv_event_name_id);
////        TextView eventDate = (TextView) eventListView.findViewById(R.id.tv_date_id);
//
//        eventName.setText(singleEventItem);
////        eventDate.setText(secondEventItem);
//        return eventListView;
//    }
//}

//class EventListviewAdapter extends BaseAdapter {
//    Context context;
//    List<EventContext> eventList;
//
//    public EventListviewAdapter(List<EventContext> eventListContent, Context context) {
//        this.context = context;
//        this.eventList = eventListContent;
//    }
//
//    @Override
//    public int getCount() {
//        return this.eventList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return this.eventList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewEvent viewEvent = null;
//
//        if (convertView == null) {
//            viewEvent = new ViewEvent();
//            LayoutInflater layoutInflater = (LayoutInflater)
//                    this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.event_listview_adapter, null);
//            viewEvent.tvEventName = (TextView) convertView.findViewById(R.id.tv_event_name_id);
//            viewEvent.tvEventDate = (TextView) convertView.findViewById(R.id.tv_date_id);
//            convertView.setTag(viewEvent);
//        } else {
//            viewEvent = (ViewEvent) convertView.getTag();
//        }
//        viewEvent.tvEventName.setText(eventList.get(position).event_name);
//        viewEvent.tvEventDate.setText(eventList.get(position).event_date);
//
//        return convertView;
//    }
//}

//class ViewEvent {
//    TextView tvEventName;
//    TextView tvEventDate;
//}

//class EventListviewAdapter extends BaseAdapter {
//    private Activity activity;
//    private LayoutInflater inflater;
//    private List<Event> events;
//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//
//    public EventListviewAdapter(Activity activity, List<Event> events) {
//        this.activity = activity;
//        this.events = events;
//    }
//    @Override
//    public int getCount() {
//        return events.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return events.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(inflater == null){
//            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//        if(convertView == null){
//            convertView = inflater.inflate(R.layout.event_listview_adapter, null);
//        }
//        if(imageLoader == null){
//            imageLoader = AppController.getInstance().getImageLoader();
//            TextView eventName = (TextView) convertView.findViewById(R.id.tv_event_name_id);
//            TextView eventDate = (TextView) convertView.findViewById(R.id.tv_date_id);
//
//            Event event = events.get(position);
//            eventName.setText(event.getEventName());
//            eventDate.setText(event.getEventDate());
//        }
//        return convertView;
//    }
//}