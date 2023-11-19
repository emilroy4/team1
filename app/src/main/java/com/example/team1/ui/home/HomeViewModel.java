package com.example.team1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> locationText;
    private final MutableLiveData<List<Announcement>> announcements;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Announcement:");

        locationText = new MutableLiveData<>();
        locationText.setValue("Location:");

        announcements = new MutableLiveData<>();
        announcements.setValue(new ArrayList<>());
        // Add initial announcements
        addAnnouncement("Training has been cancelled due to poor weather. Next training session will be on Wednesday at 11 am.");
        addAnnouncement("Make sure to pay memberships by 30th November (Friday)");

    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getLocationText() {
        return locationText;
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
