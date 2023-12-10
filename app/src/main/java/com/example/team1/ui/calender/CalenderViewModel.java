package com.example.team1.ui.calender;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalenderViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Announcement>> announcements;

    public CalenderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Upcoming Schedule:");

        // Initialize announcements with initial announcements
        List<Announcement> initialAnnouncements = new ArrayList<>();
        initialAnnouncements.add(new Announcement(new Date(), "Team practice on Monday at 5 PM."));
        initialAnnouncements.add(new Announcement(new Date(), "Important match on Saturday. Be prepared!"));

        announcements = new MutableLiveData<>();
        announcements.setValue(initialAnnouncements);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Announcement>> getAnnouncements() {
        return announcements;
    }

    public void addAnnouncement(String announcementMessage) {
        List<Announcement> currentAnnouncements = announcements.getValue();
        if (currentAnnouncements == null) {
            currentAnnouncements = new ArrayList<>();
        }
        currentAnnouncements.add(new Announcement(new Date(), announcementMessage));
        announcements.setValue(currentAnnouncements);
    }
}
