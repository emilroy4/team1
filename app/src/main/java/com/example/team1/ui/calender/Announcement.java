package com.example.team1.ui.calender;

import java.util.Date;

public class Announcement {
    private Date dateTime;
    private String message;
    private String title;
    private String location;
    private String description;

    // Constructor for announcements without title, location, and description
    public Announcement(Date dateTime, String message) {
        this.dateTime = dateTime;
        this.message = message;
    }

    // Constructor for announcements with title, location, and description
    public Announcement(Date dateTime, String message, String title, String location, String description) {
        this.dateTime = dateTime;
        this.message = message;
        this.title = title;
        this.location = location;
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
