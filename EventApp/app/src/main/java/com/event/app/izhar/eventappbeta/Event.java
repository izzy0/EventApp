package com.event.app.izhar.eventappbeta;

import java.sql.Time;

public class Event {

    private String eventDate;
    private int eventId;
    private String eventHost;
    private String eventName;
    private String eventLocation;
    private Time startTime;
    private Time endTime;

    public Event(String eventDate, String eventHost, String eventName) {
        this.eventDate = eventDate;
        this.eventHost = eventHost;
        this.eventName = eventName;
    }

    public Event(String name, String date) {
        this.eventName = name;
        this.eventDate = date;
    }

    public Event() {
    }

    public void setEventName(String name) {
        this.eventName = name;
    }

    public void setEventDate(String date) {
        this.eventDate = date;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventHost() {
        return eventHost;
    }

    public String getEventName() {
        return eventName;
    }
}
