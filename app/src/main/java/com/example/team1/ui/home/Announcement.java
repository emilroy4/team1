package com.example.team1.ui.home;

import java.util.Date;

public class Announcement {
    private Date dateTime;
    private String message;

    public Announcement(Date dateTime, String message) {
        this.dateTime = dateTime;
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }
}
