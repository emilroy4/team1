package com.example.team1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> locationText;
    private final MutableLiveData<String> announcement1;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Announcement:");

        announcement1 = new MutableLiveData<>();
        announcement1.setValue("Training has been cancelled due to poor weather, Next training session will be " +
                "on Wednesday at 11 am.");

        locationText = new MutableLiveData<>();
        locationText.setValue("Location:");



    }


    public LiveData<String> getText() {
        return mText;
    }


    public LiveData<String> getLocationText() {
        return locationText;
    }

    public LiveData<String> getAnnouncement1() {
        return announcement1;
    }

}