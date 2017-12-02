package com.event.app.izhar.eventappbeta;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Izhar on 10/7/2017.
 *
 * include gallary here .. or in a details class??
 */

public class Event {
//    private Date eventDate;
    private String eventDate;
    private String eventHost;
    private String eventName;
    private Time startTime;
    private Time endTime;

//    public Event(Date eventDate, String eventHost, String eventName){
//        this.eventDate = eventDate;
//        this.eventHost = eventHost;
//        this.eventName = eventName;
//    }
    public Event(String eventDate, String eventHost, String eventName){
        this.eventDate = eventDate;
        this.eventHost = eventHost;
        this.eventName = eventName;
    }

//    public Date getEventDate(){
//        return eventDate;
//    }
    public String getEventDate(){ return eventDate;}

    public String getEventHost(){
        return eventHost;
    }

    public String getEventName(){
        return eventName;
    }
}
